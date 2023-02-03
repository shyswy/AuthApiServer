# README

# 인증, 인가, 보안 기능을 수행하는 Rest api 서버

## Spring Security를 통안 보안 기능 수행 

## Spring Security의 Filter 영역에서 JWT 토큰을 통한 인가, 인증 수행

# Requirement

## 인증

![https://blog.kakaocdn.net/dn/cJjskz/btrX17mes7w/BIFu9dDMwBF3wX9sc6Pk0k/img.png](https://blog.kakaocdn.net/dn/cJjskz/btrX17mes7w/BIFu9dDMwBF3wX9sc6Pk0k/img.png)

## 인가

![https://blog.kakaocdn.net/dn/EgmuX/btrX1wz4uu5/O2F7lHaIRAy01z2VhoHty1/img.png](https://blog.kakaocdn.net/dn/EgmuX/btrX1wz4uu5/O2F7lHaIRAy01z2VhoHty1/img.png)

![https://blog.kakaocdn.net/dn/bwc7IA/btrXVZRwE6R/NqN5FksKkOpTuygFsdepzK/img.png](https://blog.kakaocdn.net/dn/bwc7IA/btrXVZRwE6R/NqN5FksKkOpTuygFsdepzK/img.png)

![https://blog.kakaocdn.net/dn/bqlHvo/btrXX9lzYab/A6RFeP1d3C9sUffbMSokl0/img.png](https://blog.kakaocdn.net/dn/bqlHvo/btrXX9lzYab/A6RFeP1d3C9sUffbMSokl0/img.png)

# Result

## /auth/signin

### Request

![https://blog.kakaocdn.net/dn/ZO6Sr/btrX1obfSAD/oXhqXJuXOgtYJZlx8z4O3k/img.png](https://blog.kakaocdn.net/dn/ZO6Sr/btrX1obfSAD/oXhqXJuXOgtYJZlx8z4O3k/img.png)

### Response

Success

![https://blog.kakaocdn.net/dn/dCF13R/btrXXy0ek8D/5L7Mahpq0qPhWkVcLxWer1/img.png](https://blog.kakaocdn.net/dn/dCF13R/btrXXy0ek8D/5L7Mahpq0qPhWkVcLxWer1/img.png)

Wrong id

![https://blog.kakaocdn.net/dn/FvEQD/btrXWfGPC3G/lMUuh6MXbK0w4s7aKQ05O0/img.png](https://blog.kakaocdn.net/dn/FvEQD/btrXWfGPC3G/lMUuh6MXbK0w4s7aKQ05O0/img.png)

Wrong password

![https://blog.kakaocdn.net/dn/caat6W/btrXWglsfAs/UenZwUY8ZkcNGEMho1oXiK/img.png](https://blog.kakaocdn.net/dn/caat6W/btrXWglsfAs/UenZwUY8ZkcNGEMho1oXiK/img.png)

## /auth/verification/{name}

### Request

![https://blog.kakaocdn.net/dn/tTv1l/btrX180b2bL/RJOoy9zN2kloMcOkrK0pk1/img.png](https://blog.kakaocdn.net/dn/tTv1l/btrX180b2bL/RJOoy9zN2kloMcOkrK0pk1/img.png)

### Response

Success

![https://blog.kakaocdn.net/dn/bpPnPp/btrX2m4WvdH/o7WpjJMxIE9z4u5i3s7V1K/img.png](https://blog.kakaocdn.net/dn/bpPnPp/btrX2m4WvdH/o7WpjJMxIE9z4u5i3s7V1K/img.png)

Error (bad-request)

![https://blog.kakaocdn.net/dn/kU4nh/btrXYaLXEqj/1BiEKtiiKoYlxK60noDP60/img.png](https://blog.kakaocdn.net/dn/kU4nh/btrXYaLXEqj/1BiEKtiiKoYlxK60noDP60/img.png)

## /data/name

### Request

![https://blog.kakaocdn.net/dn/bTGp7O/btrXZuDqbEZ/Wq1IKjvhEhH6jnGp8tu8f0/img.png](https://blog.kakaocdn.net/dn/bTGp7O/btrXZuDqbEZ/Wq1IKjvhEhH6jnGp8tu8f0/img.png)

### Response

Success

![https://blog.kakaocdn.net/dn/YYB6m/btrXX9zxqU0/G2CguwcbZFAakeSZAXxch1/img.png](https://blog.kakaocdn.net/dn/YYB6m/btrXX9zxqU0/G2CguwcbZFAakeSZAXxch1/img.png)# AuthApiServer
