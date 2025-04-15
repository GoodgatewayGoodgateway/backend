
# 🗃️ 유저 관련 DB 테이블 설계

---

## 📄 users (기본 정보 테이블)

| 컬럼명           | 타입         | 설명 |
|------------------|--------------|------|
| `id`             | INT, PK      | 유저 고유 ID |
| `name`           | VARCHAR      | 이름 |
| `avatar`         | VARCHAR      | 아바타 이미지 URL |
| `sex`            | VARCHAR(5)   | 성별 ('남', '여') |
| `age`            | INT          | 나이 |
| `job`            | VARCHAR      | 직업 |
| `introduction`   | TEXT         | 간단한 소개문 |
| `location`       | VARCHAR      | 거주 지역 |
| `mbti`           | VARCHAR(10)  | MBTI |
| `ideal_roommate` | TEXT         | 원하는 룸메이트 스타일 설명 |
| `smoking`        | BOOLEAN      | 흡연 여부 |
| `drinking`       | BOOLEAN      | 음주 여부 |
| `online`         | BOOLEAN      | 현재 온라인 여부 |
| `last_seen`      | DATETIME     | 마지막 접속 시각 |

---

## 📄 user_interests (자유입력 관심사 - 다대다)

| 컬럼명       | 타입     | 설명 |
|--------------|----------|------|
| `id`         | INT, PK  | 고유 ID |
| `user_id`    | INT, FK  | `users.id` 참조 |
| `interest`   | VARCHAR  | 관심사 내용 |

---

## 📄 user_lifestyle (간단한 생활 패턴)

| 컬럼명            | 타입     | 설명 |
|-------------------|----------|------|
| `id`              | INT, PK  | 고유 ID |
| `user_id`         | INT, FK  | `users.id` 참조 |
| `wake_time`       | TIME     | 기상 시간 |
| `sleep_time`      | TIME     | 취침 시간 |
| `clean_level`     | INT      | 청소 수준 (예: 1~5 등급) |
| `noise_tolerance` | INT      | 소음 수용도 (예: 1~5 등급) |

---

## 📄 user_preferences (선호 및 생활 습관)

| 컬럼명              | 타입      | 설명 |
|---------------------|-----------|------|
| `id`                | INT, PK   | 고유 ID |
| `user_id`           | INT, FK   | `users.id` 참조 |
| `meal_time`         | TIME      | 식사 시간 |
| `kitchen_usage`     | VARCHAR   | 주방 사용 빈도 |
| `cooking_freq`      | VARCHAR   | 요리 빈도 |
| `tidy_level`        | VARCHAR   | 정리 수준 |
| `clean_freq`        | VARCHAR   | 청소 주기 |
| `common_area_mgmt`  | VARCHAR   | 공용공간 관리 성향 |
| `sound_sensitivity` | VARCHAR   | 소음 민감도 |
| `music_tv_pref`     | VARCHAR   | 음악/TV 볼륨 선호 |
| `pet_allowed`       | BOOLEAN   | 반려동물 허용 여부 |
| `preferred_pet`     | VARCHAR   | 선호하는 동물 종류 |
| `pet_allergy`       | BOOLEAN   | 반려동물 알레르기 유무 |

---

## 🔗 테이블 관계 요약

- `users` 1 : N `user_interests`
- `users` 1 : 1 `user_lifestyle`
- `users` 1 : 1 `user_preferences`
