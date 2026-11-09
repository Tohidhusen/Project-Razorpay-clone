package com.project.RazorpayClone.common.ratelimit;

public interface RateLimiter {
 RateLimitResult check(String key,int maxRequestAllowed,long windowSecond);

}
