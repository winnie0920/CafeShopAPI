package com.cafeshop.interceptor;
import com.cafeshop.CurrentHolder;
import com.cafeshop.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 令牌校驗的攔截器
 */
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // ⭐ 放行 OPTIONS（CORS 預檢）
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        // 3. 獲取請求中的token
        String token = request.getHeader("token");
        // 4. 判斷token是否存在，如果不存在說明用戶沒有登入，返回錯誤訊息(401 status)
        if(token == null || token.isEmpty()){
            log.info("令牌為空，響應401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        // 5. 如果token存在，校驗令牌，如果校驗失敗 → 返回錯誤訊息(401 status)
        try {
            Claims claims = JwtUtils.parseToken(token);
            Integer empId =  Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(empId); // 存入threadLocal當前員工ID

            log.info("當前登入員工ID：{}，將其存入ThreadLocal",empId);
        } catch (Exception e) {
            log.info("令牌為空，響應401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        // 6. 校驗通過，放行
        log.info("令牌合法，放行");
        return true;
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 7. 刪除TheadLocal中的資料
        CurrentHolder.remove();
    }
}

