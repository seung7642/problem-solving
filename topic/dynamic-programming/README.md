# Dynamic Programming

Dynamic Programming 알고리즘은 두 가지 방식으로 나뉜다.
1. Top-Down (Memoization) 방식
2. Bottom-Up (Tabulation) 방식


#### Top-Down (Memoization) 방식
Top-Down (Memoization) 방식의 특징
- 큰 문제에서 작은 문제로 분할하며 해결한다.
- 주로 재귀를 사용하지만, 이는 구현 방식일 뿐 필수는 아니다.
- 필요한 부분만 계산하는 "lazy evaluation" 특성을 가진다.
- 호출 스택으로 인한 오버헤드가 존재한다.
  
Top-Down 방식이 Memoization 방식이라고도 불리는 이유
- 계산한 결과를 "메모" 해두고 재사용하기 때문
- 필요할 때마다 메모를 확인하고, 없으면 계산하여 저장한다.
- 주로 해시 테이블(딕셔너리)을 사용한다.

#### Bottom-Up (Tabulation) 방식
Bottom-Up (Tabulation) 방식의 특징
- 작은 문제부터 시작해서 큰 문제를 해결한다.
- 주로 반복문을 사용하지만, 이것도 구현 방식일 뿐 필수는 아니다.
- 모든 부분 문제를 순차적으로 계산한다.
- 일반적으로 Top-Down(Memoization) 방식에 비해 더 효율적으로 메모리를 사용한다.

Bottom-Up 방식이 Tabulation 방식이라고도 불리는 이유
- Table(표)에서 유래
- 결과를 표 형태의 배열에 순차적으로 채운다.
- 작은 값부터 차례대로 테이블을 완성한다.
- 주로 배열(리스트)을 사용한다.

## Q&A
1. Top-Down(Memoization) 방식으로 해결할 수 있는 문제는 Bottom-Up(Tabulation) 방식으로도 해결할 수 있고, 그 반대도 성립하는지?
    - 이론적으로는 대부분의 DP 문제가 두 방식으로 모두 해결 가능하지만, 실제로는 한 방식이 더 자연스럽거나 효율적인 경우가 많다.