# Fault Injection

Muc tieu cua phan nay la co tinh lam hong mot so logic UI de chung minh bo test Katalon phat hien loi.

Quy trinh khuyen nghi:

1. Dam bao `Test Suites/Baseline` PASS 100% tren code sach.
2. Tao nhanh rieng, vi du `fault-injection/katalon-demo`.
3. Ap dung `fault-injection.patch`.
4. Chay `Test Suites/FaultInjection`.
5. Xuat ket qua bang `tools/export-clean-results.ps1`.
6. Hoan tac patch hoac bo nhanh fault injection sau khi lay du lieu.

Test du kien fail:

- `TC-005`: staff login bi dieu huong sai route.
- `TC-009`, `TC-013`, `TC-015`, `TC-020`: input search bi doi selector.
- `TC-011`: filter bao hiem benh nhan bi dao logic.
- `TC-016`: filter trang thai phong bi dao logic.
- `TC-019`: tab appointment khong con chuyen dung trang thai.
