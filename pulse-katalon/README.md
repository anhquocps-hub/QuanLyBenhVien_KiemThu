# Pulse Clinic – Hướng Dẫn Chạy Project & Katalon Test

---

## Mục lục

1. [Yêu cầu](#1-yêu-cầu)
2. [Bước 1 – Thêm subdomains vào hosts file (Windows)](#2-bước-1--thêm-subdomains-vào-hosts-file-windows)
3. [Bước 2 – Khởi động database và Redis bằng Docker](#3-bước-2--khởi-động-database-và-redis-bằng-docker)
4. [Bước 3 – Tạo application.properties cho backend](#4-bước-3--tạo-applicationproperties-cho-backend)
5. [Bước 4 – Chạy backend (pulse-server)](#5-bước-4--chạy-backend-pulse-server)
6. [Bước 5 – Chạy frontend (pulse-web)](#6-bước-5--chạy-frontend-pulse-web)
7. [Bước 6 – Mở project trong Katalon Studio](#7-bước-6--mở-project-trong-katalon-studio)
8. [Bước 7 – Chạy Baseline (Pass 100%)](#8-bước-7--chạy-baseline-pass-100)
9. [Bước 8 – Tiêm lỗi và chạy FaultInjection](#9-bước-8--tiêm-lỗi-và-chạy-faultinjection)
10. [Bước 9 – Xuất file CSV sạch](#10-bước-9--xuất-file-csv-sạch)
11. [Tài khoản test](#11-tài-khoản-test)

---

## 1. Yêu cầu

| Phần mềm | Phiên bản tối thiểu | Ghi chú |
|---|---|---|
| Docker Desktop | bất kỳ | Đã cài sẵn |
| JDK | 21 | Để chạy Spring Boot |
| Node.js | 18+ | Để chạy Next.js |
| Katalon Studio | 9.x hoặc mới hơn | Đã cài sẵn |
| Git | bất kỳ | Để áp dụng fault-injection.patch |

---

## 2. Bước 1 – Thêm subdomains vào hosts file (Windows)

Frontend dùng subdomain routing (`staff.localhost`, `doctor.localhost`, `hms.localhost`). Windows không tự phân giải subdomains của localhost nên cần thêm tay một lần.

**Chạy Notepad với quyền Administrator**, mở file:

```
C:\Windows\System32\drivers\etc\hosts
```

Thêm 3 dòng sau vào cuối file rồi lưu:

```
127.0.0.1    hms.localhost
127.0.0.1    staff.localhost
127.0.0.1    doctor.localhost
```

> **Lưu ý:** Nếu Notepad không cho lưu do thiếu quyền, tìm Notepad trong Start Menu, click chuột phải chọn **Run as Administrator**, sau đó mở file đó.

---

## 3. Bước 2 – Khởi động database và Redis bằng Docker

Các container `pulse-server-postgres-1` và `pulse-server-redis-1` đã từng chạy trên máy. Để khởi động lại:

```powershell
# Mở terminal tại thư mục pulse-server
cd QuanLyBenhVien_KiemThu\pulse-server

# Khởi động postgres (port 5433) + redis (port 6379)
docker compose up -d postgres redis
```

Kiểm tra các container đang chạy:

```powershell
docker ps
```

Kết quả mong đợi có 2 dòng:

```
pulse-server-postgres-1   postgres:latest   Up ...   0.0.0.0:5433->5432/tcp
pulse-server-redis-1      redis:7-alpine    Up ...   0.0.0.0:6379->6379/tcp
```

> Container `postgres-test` (port 5434) chỉ dùng khi chạy unit test của backend, không cần thiết cho Katalon.

---

## 4. Bước 3 – Tạo application.properties cho backend

Backend không có file `application.properties` trong source code (được cấu hình qua biến môi trường khi deploy). Cần tạo file này thủ công để chạy local.

Tạo file mới tại:

```
QuanLyBenhVien_KiemThu\pulse-server\src\main\resources\application.properties
```

Nội dung file (copy nguyên):

```properties
spring.application.name=Pulse Clinic API

# Tắt tự động phát hiện compose.yml – bắt buộc, nếu thiếu sẽ lỗi khi khởi động
spring.docker.compose.enabled=false

# Database – kết nối tới container postgres chạy ở cổng 5433
spring.datasource.url=jdbc:postgresql://localhost:5433/pulse-db
spring.datasource.username=user
spring.datasource.password=135792468
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.jdbc.time_zone=Asia/Ho_Chi_Minh

# Flyway – tự tạo bảng khi khởi động
spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration
spring.flyway.baseline-on-migrate=true
spring.flyway.baseline-version=1
spring.flyway.detect-encoding=true

# JWT
jwt.secret=b0d86673d17c9b9c3203a7dba9b8d5696423d3480aed6408088fb45907318ccc
jwt.expiration=86400000

# Redis
spring.data.redis.host=localhost
spring.data.redis.port=6379

# Mail – dùng giá trị giả, không gửi email thật khi test Katalon
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=test@example.com
spring.mail.password=testpassword
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

# OTP
app.otp.ttl-minutes=10
app.otp.request-cooldown-seconds=60
app.otp.max-attempts=5
app.otp.pepper=local-test-pepper

# Supabase – dùng giá trị giả, tính năng upload ảnh không cần cho Katalon
supabase.url=https://placeholder.supabase.co
supabase.key=placeholder-key
supabase.bucket.avatars=avatars

# Gemini AI – dùng giá trị giả, tính năng chat AI không cần cho Katalon
gemini.api-key=placeholder-key

# Upload
spring.servlet.multipart.max-file-size=5MB
spring.servlet.multipart.max-request-size=5MB

# CORS – cho phép frontend local
cors.allowed-origins=http://localhost:3000,http://hms.localhost:3000,http://staff.localhost:3000,http://doctor.localhost:3000
```

> File này **không commit** lên git (đã có trong `.gitignore` của pulse-server).

---

## 5. Bước 4 – Chạy backend (pulse-server)

**Cách 1: Maven Wrapper (terminal)**

```powershell
cd QuanLyBenhVien_KiemThu\pulse-server

# Windows
.\mvnw.cmd spring-boot:run
```

**Cách 2: IntelliJ IDEA**

Mở thư mục `pulse-server` trong IntelliJ → Run `PulseClinicApiApplication`. Nếu báo lỗi _"Port 8080 already in use"_, kiểm tra và dừng process đang chiếm cổng 8080 trước:

```powershell
$p = (Get-NetTCPConnection -LocalPort 8080 -State Listen).OwningProcess
Stop-Process -Id $p -Force
```

Backend sẽ khởi động tại `http://localhost:8080`. Flyway tự chạy migration và DataSeeder tự seed dữ liệu demo vào lần đầu.

Kiểm tra backend đã sẵn sàng bằng cách mở trình duyệt:

```
http://localhost:8080/v3/api-docs
```

Thấy JSON là backend đã chạy.

---

## 6. Bước 5 – Chạy frontend (pulse-web)

```powershell
cd QuanLyBenhVien_KiemThu\pulse-web

# Cài package nếu chưa cài
npm install

# Tạo file .env.local với biến trỏ về backend local
# Lưu ý: BACKEND_API_URL không có http:// ở đầu
echo BACKEND_API_URL=localhost:8080 > .env.local
echo ROOT_DOMAIN=localhost:3000 >> .env.local

# Chạy dev server
npm run dev
```

Frontend chạy tại `http://localhost:3000`. Nhờ bước 1 đã cấu hình hosts, các URL subdomain sau cũng hoạt động:

| URL | Vai trò |
|---|---|
| `http://hms.localhost:3000` | Chọn vai trò (Staff / Doctor) |
| `http://staff.localhost:3000/login` | Đăng nhập Staff |
| `http://doctor.localhost:3000/login` | Đăng nhập Doctor |
| `http://localhost:3000/login` | Đăng nhập Patient |

---

## 7. Bước 6 – Mở project trong Katalon Studio

1. Mở **Katalon Studio**.
2. Chọn **File → Open Project**.
3. Trong dialog **"Select Folder"**, điều hướng đến thư mục cha (`QuanLyBenhVien_KiemThu`), click vào thư mục **`pulse-katalon`** để chọn nó, rồi bấm **"Select Folder"**.
   > Katalon 11.x mở project theo **thư mục** (không phải file `.prj`). Không cần vào bên trong `pulse-katalon`.
4. Nếu đang có project khác mở, Katalon tự động chuyển sang project mới mà không cần đóng trước.
5. Katalon sẽ load project và hiển thị cây thư mục Test Cases, Test Suites, Keywords.

---

## 8. Bước 7 – Chạy Baseline (Pass 100%)

> **Đã kiểm tra với Katalon Studio Enterprise 11.0.1** trên Windows 11.

**Yêu cầu WebDriver:** Katalon tự quản lý ChromeDriver (tải về tự động). Đảm bảo có kết nối internet lần đầu chạy.

1. Trong Katalon Studio, mở **Test Suites → Baseline**.
2. Click **Run** (nút tam giác xanh).
3. Chọn trình duyệt **Chrome**.
4. Đợi chạy xong (~5–10 phút). Kết quả mong đợi: **20/20 PASS**.

> Nếu tất cả test báo **"Error"** với thời gian < 100ms/test: đây là lỗi biên dịch keyword. Đảm bảo đã dùng đúng phiên bản file `Keywords/pulseclinic/WebUiKeywords.groovy` trong repo (không dùng `WebUiCommonHelper`).

---

## 9. Bước 8 – Tiêm lỗi và chạy FaultInjection

**Tạo nhánh riêng trước khi áp dụng lỗi:**

```powershell
cd QuanLyBenhVien_KiemThu
git checkout -b katalon/fault-injection-demo
```

**Áp dụng patch tiêm lỗi:**

```powershell
git apply pulse-katalon\fault-injection\fault-injection.patch
```

**Khởi động lại frontend** để phản ánh code mới (nhấn `Ctrl+C` ở terminal đang chạy `npm run dev` rồi chạy lại).

**Chạy Test Suite FaultInjection trong Katalon:**
1. Mở **Test Suites → FaultInjection**.
2. Click **Run**.
3. Kết quả mong đợi – các test case sau **FAIL**:

| Test Case | Lý do fail |
|---|---|
| TC-005 | Staff login điều hướng sai route |
| TC-009, TC-013, TC-015, TC-020 | Search input bị đổi selector |
| TC-011 | Filter bảo hiểm bệnh nhân bị đảo logic |
| TC-016 | Filter trạng thái phòng bị đảo logic |
| TC-019 | Tab appointment không chuyển đúng |

**Hoàn tác sau khi lấy kết quả:**

```powershell
git checkout pulse-web\
git checkout main
```

---

## 10. Bước 9 – Xuất file CSV sạch

### Cách 1: Dùng PowerShell (ngoài Katalon)

```powershell
cd QuanLyBenhVien_KiemThu
.\pulse-katalon\tools\export-clean-results.ps1
```

File xuất ra tại:

```
pulse-katalon\Reports\katalon-test-results.csv
```

### Cách 2: Chạy trong Katalon Studio

Trong Katalon, mở và chạy test case **Test Cases → Shared → ExportCleanResults**.

### Định dạng file CSV giao nộp

```csv
ID Bài Test,Trạng thái Pass/Fail,Thời gian chạy
TC-001,PASS,2.31s
TC-005,FAIL,5.82s
TC-011,FAIL,3.44s
```

---

## 11. Tài khoản test

| Vai trò | Email | Mật khẩu |
|---|---|---|
| Staff | `pharmacist1@hospital.com` | `password123` |
| Doctor | `admin.doctor@hospital.com` | `password123` |
| Patient | `patient1@example.com` | `password123` |
