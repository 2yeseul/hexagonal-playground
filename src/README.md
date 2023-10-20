# Hexagonal Architecture
![image](https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FdodJH5%2FbtrocBYuCNj%2FJZxyhHwK0g4mSq7dPcUCtk%2Fimg.png)

## 헥사고날의 목적
가장 큰 목적은 외부의 변화에 빠르게 대응할 수 있도록 한다는 것이다.
외부의 변화에 빠르게 대응한다는 것은 곧 유지보수가 쉬운 아키텍처이다.
외부 의존성은 내부 컴포넌트와 완전히 격리되어 있다. 의존성 역전을 통해 모든 의존성이 도메인 코드를 향한다.
