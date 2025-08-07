package com.circuitBreakerResiliancePOC.controller;


import com.circuitBreakerResiliancePOC.service.UnstableService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestAPIUnstable {
        private  final UnstableService unstableService;
        public  TestAPIUnstable(UnstableService unstableService)
        {
            this.unstableService = unstableService;
        }
        @GetMapping("/test")
    public  String testEndpoints()
        {
            try {
                return  unstableService.unstableMethod();
            }
            catch (Exception ex)
            {
                return  "Fallback: Service unavailable!\"";
            }
        }
}
