# README

# README

# backend-api-auth

API 서버를 통한 보안, 인증, 인가 기능을 구현한 프로젝트

- Spring Security를 통안 보안 기능 수행
- Spring Security의 Filter 영역에서 JWT 토큰을 통한 인증, 인가 수행
- User CRUD 메소드에서 권한에 따른 인가 수행

# Requirement

## Authenticatoin & Authorization

### Authentication

![https://blog.kakaocdn.net/dn/cJjskz/btrX17mes7w/BIFu9dDMwBF3wX9sc6Pk0k/img.png](https://blog.kakaocdn.net/dn/cJjskz/btrX17mes7w/BIFu9dDMwBF3wX9sc6Pk0k/img.png)

### Authorization

![https://blog.kakaocdn.net/dn/EgmuX/btrX1wz4uu5/O2F7lHaIRAy01z2VhoHty1/img.png](https://blog.kakaocdn.net/dn/EgmuX/btrX1wz4uu5/O2F7lHaIRAy01z2VhoHty1/img.png)

![https://blog.kakaocdn.net/dn/bwc7IA/btrXVZRwE6R/NqN5FksKkOpTuygFsdepzK/img.png](https://blog.kakaocdn.net/dn/bwc7IA/btrXVZRwE6R/NqN5FksKkOpTuygFsdepzK/img.png)

![https://blog.kakaocdn.net/dn/bqlHvo/btrXX9lzYab/A6RFeP1d3C9sUffbMSokl0/img.png](https://blog.kakaocdn.net/dn/bqlHvo/btrXX9lzYab/A6RFeP1d3C9sUffbMSokl0/img.png)

## User CRUD

![Untitled](README%20d24843fc383845bb99db0b01ec27fd06/Untitled.png)

![Untitled](README%20d24843fc383845bb99db0b01ec27fd06/Untitled%201.png)

![Untitled](README%20d24843fc383845bb99db0b01ec27fd06/Untitled%202.png)

![Untitled](README%20d24843fc383845bb99db0b01ec27fd06/Untitled%203.png)

![Untitled](README%20d24843fc383845bb99db0b01ec27fd06/Untitled%204.png)

# Result

## Authenticatoin & Authorization

### /auth/signin

**Request**

![https://blog.kakaocdn.net/dn/ZO6Sr/btrX1obfSAD/oXhqXJuXOgtYJZlx8z4O3k/img.png](https://blog.kakaocdn.net/dn/ZO6Sr/btrX1obfSAD/oXhqXJuXOgtYJZlx8z4O3k/img.png)

**Response**

Success

![https://blog.kakaocdn.net/dn/dCF13R/btrXXy0ek8D/5L7Mahpq0qPhWkVcLxWer1/img.png](https://blog.kakaocdn.net/dn/dCF13R/btrXXy0ek8D/5L7Mahpq0qPhWkVcLxWer1/img.png)

Wrong id

![https://blog.kakaocdn.net/dn/FvEQD/btrXWfGPC3G/lMUuh6MXbK0w4s7aKQ05O0/img.png](https://blog.kakaocdn.net/dn/FvEQD/btrXWfGPC3G/lMUuh6MXbK0w4s7aKQ05O0/img.png)

Wrong password

![https://blog.kakaocdn.net/dn/caat6W/btrXWglsfAs/UenZwUY8ZkcNGEMho1oXiK/img.png](https://blog.kakaocdn.net/dn/caat6W/btrXWglsfAs/UenZwUY8ZkcNGEMho1oXiK/img.png)

### /auth/verification/{name}

**Request**

![https://blog.kakaocdn.net/dn/tTv1l/btrX180b2bL/RJOoy9zN2kloMcOkrK0pk1/img.png](https://blog.kakaocdn.net/dn/tTv1l/btrX180b2bL/RJOoy9zN2kloMcOkrK0pk1/img.png)

**Response**

Success

![https://blog.kakaocdn.net/dn/bpPnPp/btrX2m4WvdH/o7WpjJMxIE9z4u5i3s7V1K/img.png](https://blog.kakaocdn.net/dn/bpPnPp/btrX2m4WvdH/o7WpjJMxIE9z4u5i3s7V1K/img.png)

Error (bad-request)

![https://blog.kakaocdn.net/dn/kU4nh/btrXYaLXEqj/1BiEKtiiKoYlxK60noDP60/img.png](https://blog.kakaocdn.net/dn/kU4nh/btrXYaLXEqj/1BiEKtiiKoYlxK60noDP60/img.png)

### /data/name

**Request**

![https://blog.kakaocdn.net/dn/bTGp7O/btrXZuDqbEZ/Wq1IKjvhEhH6jnGp8tu8f0/img.png](https://blog.kakaocdn.net/dn/bTGp7O/btrXZuDqbEZ/Wq1IKjvhEhH6jnGp8tu8f0/img.png)

**Response**

Success

![https://blog.kakaocdn.net/dn/YYB6m/btrXX9zxqU0/G2CguwcbZFAakeSZAXxch1/img.png](https://blog.kakaocdn.net/dn/YYB6m/btrXX9zxqU0/G2CguwcbZFAakeSZAXxch1/img.png)

## User CRUD

### /user/signup

**Request**

![https://blog.kakaocdn.net/dn/d5Th26/btrYz9LdVmM/waUyRUIWkAsQHdogy8A540/img.png](https://blog.kakaocdn.net/dn/d5Th26/btrYz9LdVmM/waUyRUIWkAsQHdogy8A540/img.png)

**Response**

**Success**

![https://blog.kakaocdn.net/dn/nZBxg/btrYz8ZVSiP/3F5J4pwsxKBfJcVKA0Y53K/img.png](https://blog.kakaocdn.net/dn/nZBxg/btrYz8ZVSiP/3F5J4pwsxKBfJcVKA0Y53K/img.png)

**Error: 이미 존재하는 유저일시** 

![https://blog.kakaocdn.net/dn/2I2nY/btrYzaYtT41/bszRIjEzilUYGT0lGnZ5h0/img.png](https://blog.kakaocdn.net/dn/2I2nY/btrYzaYtT41/bszRIjEzilUYGT0lGnZ5h0/img.png)

### /user/read/{id}

![https://blog.kakaocdn.net/dn/Ypc0F/btrYtwuw4it/MK1w5ySYmnqSDvAiEnTTQ0/img.png](https://blog.kakaocdn.net/dn/Ypc0F/btrYtwuw4it/MK1w5ySYmnqSDvAiEnTTQ0/img.png)

![https://blog.kakaocdn.net/dn/bKiibv/btrYzaRILhr/2Gj4nnkyCLQCQjD9Y4dQCK/img.png](https://blog.kakaocdn.net/dn/bKiibv/btrYzaRILhr/2Gj4nnkyCLQCQjD9Y4dQCK/img.png)

Error

![Untitled](README%20d24843fc383845bb99db0b01ec27fd06/Untitled%205.png)

### /user/remove/{id}

**Reqeust**

![https://blog.kakaocdn.net/dn/s29xK/btrYy9eaJY3/6y2kgbwKO7TMk3H5rOglIK/img.png](https://blog.kakaocdn.net/dn/s29xK/btrYy9eaJY3/6y2kgbwKO7TMk3H5rOglIK/img.png)

**Response**

Success

![https://blog.kakaocdn.net/dn/WrusJ/btrYtxGWK9z/ZjUHZ6vmztAMTd5EvYEZ5k/img.png](https://blog.kakaocdn.net/dn/WrusJ/btrYtxGWK9z/ZjUHZ6vmztAMTd5EvYEZ5k/img.png)

토큰속 id 정보와 PathVariable로 들어온 id 정보가 일치한다면, 성공적으로 해당 유저를 제거한다.

Error

![Untitled](README%20d24843fc383845bb99db0b01ec27fd06/Untitled%206.png)

### /user/modify/{id}

**Request**

![https://blog.kakaocdn.net/dn/dPYuMs/btrYARpV9HL/eocIZdyT8eoKlC3xKbElkk/img.png](https://blog.kakaocdn.net/dn/dPYuMs/btrYARpV9HL/eocIZdyT8eoKlC3xKbElkk/img.png)

토큰 속 username 과 pathvariable이 같은지 filter에서 검증

pathvariable로 들어온 id값과 Body에 들어있는 DTO의 id가 같은지 검증.

**Response**

Success

![https://blog.kakaocdn.net/dn/cHtNHA/btrYvlMRvvU/TK9NchDwMn62IK9IaQx4Ak/img.png](https://blog.kakaocdn.net/dn/cHtNHA/btrYvlMRvvU/TK9NchDwMn62IK9IaQx4Ak/img.png)

Error

![Untitled](README%20d24843fc383845bb99db0b01ec27fd06/Untitled%207.png)