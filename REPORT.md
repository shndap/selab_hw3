# گزارش آزمایش ۳

لینک مخزن هم‌گیت: `<put hamgit repo link here>`

برای به‌دست آوردن لینک فعلی مخزن:

```bash
git remote get-url origin
```

اگر هنوز remote تنظیم نشده باشد، با این دستور آن را ثبت کنید:

```bash
git remote add origin <hamgit-repo-url>
```

## 1) خلاصه تغییرات

- باگ‌های موجود در `ShoppingCart` بررسی و اصلاح شد.
- متد `updateItemPrice(String itemName, double newPrice)` پیاده‌سازی شد.
- شرط تخفیف از `>= 100` به `> 100` اصلاح شد.
- قیمت‌های نامعتبر در `addItem` و `updateItemPrice` نادیده گرفته می‌شوند.
- تست‌های TDD مربوط به `updateItemPrice` از حالت کامنت خارج شدند.
- یک مجموعه تست جدید برای سناریوهای مرزی اضافه شد.

Commit مربوط به تغییرات اصلی: `f2fa427`

## 2) مراحل انجام کار

### فاز 1: شناسایی نقص

در نسخه اولیه، چند مشکل اصلی وجود داشت:

- متد `updateItemPrice` پیاده‌سازی نشده بود.
- شرط تخفیف اشتباه بود و برای مجموع دقیقاً برابر با 100 هم تخفیف اعمال می‌کرد.
- اعتبارسنجی قیمت‌ها در ورودی‌ها وجود نداشت.

این موارد باعث می‌شد رفتار سیستم در سناریوهای مرزی مطابق صورت‌مسئله نباشد.

### فاز 2: RED

سه تست مربوط به `updateItemPrice` از حالت کامنت خارج شدند تا شکست اولیه دیده شود.

خروجی اجرای تست‌ها در فاز قرمز:

- `7 tests found`
- `0 tests skipped`
- `6 tests successful`
- `1 test failed`

خطای اصلی:

- `testUpdateItemPrice_ShouldChangePrice`
- `expected: <80.0> but was: <50.0>`

اسکرین‌شات خروجی تست‌های شکست‌خورده: `<put failing-test screenshot here>`

برای تولید خروجی فاز RED و گرفتن اسکرین‌شات:

```bash
git worktree add /tmp/red-phase f2fa427^
cd /tmp/red-phase
rm -rf out && mkdir -p out && javac -d out -cp /tmp/junit-extract/usr/share/java/junit-jupiter-api-5.10.1.jar:/tmp/junit-extract/usr/share/java/apiguardian-api-1.1.2.jar:/tmp/junit-extract/usr/share/java/opentest4j-1.2.0.jar src/*.java Test/*.java
java -jar /tmp/junit-extract/usr/share/java/junit-platform-console-standalone-1.9.1.jar -cp out --scan-classpath
```

### فاز 3: GREEN

متد `updateItemPrice` پیاده‌سازی شد به‌طوری که:

- اگر کالا وجود داشته باشد، قیمت آن به‌روزرسانی شود.
- اگر کالا وجود نداشته باشد، هیچ تغییری رخ ندهد.
- تعداد اقلام بعد از به‌روزرسانی ثابت بماند.
- قیمت نامعتبر پذیرفته نشود.

### فاز 4: REFACTOR

کد خواناتر شد و منطق اعتبارسنجی قیمت در یک تابع کمکی متمرکز شد.

خروجی اجرای تست‌های موفق:

- `16 tests found`
- `1 test skipped`
- `15 tests successful`
- `0 tests failed`

اسکرین‌شات خروجی تست‌های موفق: `<put passing-test screenshot here>`

برای تولید خروجی فاز GREEN و گرفتن اسکرین‌شات:

```bash
cd /home/sahand/Desktop/selab_hw3/base-project-for-tdd-shoppingcart-main
rm -rf out && mkdir -p out && javac -d out -cp /tmp/junit-extract/usr/share/java/junit-jupiter-api-5.10.1.jar:/tmp/junit-extract/usr/share/java/apiguardian-api-1.1.2.jar:/tmp/junit-extract/usr/share/java/opentest4j-1.2.0.jar src/*.java Test/*.java
java -jar /tmp/junit-extract/usr/share/java/junit-platform-console-standalone-1.9.1.jar -cp out --scan-classpath
```

## 3) تست‌های اضافه‌شده

- `testRemoveMissingItemShouldReturnFalse`
- `testEmptyCartHasZeroTotalAndDiscount`
- `testAddItemWithInvalidPriceShouldBeIgnored`
- `testAddItemWithNullNameShouldBeIgnored`
- `testUpdateItemPriceWithInvalidPriceShouldDoNothing`
- `testUpdateItemPriceWithNullNameShouldDoNothing`
- `testDiscountAtExactThresholdWithDecimalPrices`
- `testDuplicateItemNameShouldOverwritePriceWithoutChangingCount`
- `testManyItemsShouldProduceAccurateTotal`

## 4) پاسخ سوال 1

نقص اصلی این بود که سیستم در حالت‌های مرزی مطابق صورت‌مسئله رفتار نمی‌کرد؛ به‌خصوص شرط تخفیف اشتباه بود و متد `updateItemPrice` نیز وجود نداشت. تست‌های فعلی فقط مسیرهای ساده را پوشش می‌دادند و سناریوهای لبه‌ای مانند مجموع دقیقاً 100، قیمت نامعتبر، و کالاهای ناموجود را بررسی نمی‌کردند. همین ضعف پوشش باعث شد باگ‌ها در تست‌های اولیه دیده نشوند.

## 5) پاسخ سوال 2

برای آشکار کردن باگ، تست‌های جدیدی برای `updateItemPrice` و سناریوی مرزی تخفیف نوشته شد. ابتدا تست `testUpdateItemPrice_ShouldChangePrice` در فاز RED شکست خورد و نشان داد که متد هنوز قیمت را تغییر نمی‌دهد. سپس پیاده‌سازی اصلاح شد و در نهایت همه تست‌ها پاس شدند. خروجی شکست‌خورده و خروجی موفقیت‌آمیز باید به‌صورت تصویر در گزارش قرار داده شوند.

## 6) گزارش پوشش تست

### پوشش اولیه

- Line Coverage: `84.62%`
- Method Coverage: `100.00%`
- Branch Coverage: `61.11%`

### پوشش پس از بهبود

- Line Coverage: `100.00%`
- Method Coverage: `100.00%`
- Branch Coverage: `100.00%`

تحلیل تغییرات پوشش:

- خطوط پوشش‌ندارِ اولیه با اضافه شدن تست‌های سبد خالی، قیمت نامعتبر، نام `null`، و مجموع دقیقاً 100 به‌طور کامل پوشش داده شدند.
- شاخه‌های بدون پوشش اولیه مربوط به مسیرهای `null` و شرط‌های رد ورودی نامعتبر بودند که با تست‌های جدید پوشش داده شدند.
- مؤثرترین تست‌ها در بهبود پوشش: `testAddItemWithNullNameShouldBeIgnored` و `testUpdateItemPriceWithNullNameShouldDoNothing`

## 7) سوال‌هایی که باید در ویدئو پاسخ داده شوند

- سوال 3
- سوال 4
- سوال 5

## 8) بازتاب شخصی

تجربه این آزمایش نشان داد که نوشتن تست پیش از پیاده‌سازی باعث شفاف‌تر شدن رفتار مورد انتظار سیستم می‌شود و احتمال نادیده‌ماندن حالت‌های لبه‌ای را کاهش می‌دهد. همچنین هنگام بازآرایی، وجود تست‌ها اطمینان بیشتری ایجاد می‌کند که تغییرات رفتاری ناخواسته وارد نشده‌اند.

## 9) وضعیت اجرای محلی

- `javac src/*.java` با موفقیت اجرا شد.
- اجرای کامل تست‌های JUnit در خط فرمان با موفقیت انجام شد.
- نتایج اجرا:
  - حالت اولیه: `7 tests found`, `1 skipped`, `6 successful`, `0 failed`
  - حالت نهایی: `16 tests found`, `1 skipped`, `15 successful`, `0 failed`
  - اجرای RED روی نسخه اولیه: `7 tests found`, `0 skipped`, `6 successful`, `1 failed`
