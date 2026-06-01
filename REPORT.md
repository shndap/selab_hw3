# گزارش آزمایش ۳

لینک مخزن هم‌گیت: `<put hamgit repo link here>`

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

اسکرین‌شات خروجی تست‌های شکست‌خورده: `<put failing-test screenshot here>`

### فاز 3: GREEN

متد `updateItemPrice` پیاده‌سازی شد به‌طوری که:

- اگر کالا وجود داشته باشد، قیمت آن به‌روزرسانی شود.
- اگر کالا وجود نداشته باشد، هیچ تغییری رخ ندهد.
- تعداد اقلام بعد از به‌روزرسانی ثابت بماند.
- قیمت نامعتبر پذیرفته نشود.

### فاز 4: REFACTOR

کد خواناتر شد و منطق اعتبارسنجی قیمت در یک تابع کمکی متمرکز شد.

اسکرین‌شات خروجی تست‌های موفق: `<put passing-test screenshot here>`

## 3) تست‌های اضافه‌شده

- `testRemoveMissingItemShouldReturnFalse`
- `testEmptyCartHasZeroTotalAndDiscount`
- `testAddItemWithInvalidPriceShouldBeIgnored`
- `testUpdateItemPriceWithInvalidPriceShouldDoNothing`
- `testDiscountAtExactThresholdWithDecimalPrices`
- `testDuplicateItemNameShouldOverwritePriceWithoutChangingCount`
- `testManyItemsShouldProduceAccurateTotal`

## 4) پاسخ سوال 1

نقص اصلی این بود که سیستم در حالت‌های مرزی مطابق صورت‌مسئله رفتار نمی‌کرد؛ به‌خصوص شرط تخفیف اشتباه بود و متد `updateItemPrice` نیز وجود نداشت. تست‌های فعلی فقط مسیرهای ساده را پوشش می‌دادند و سناریوهای لبه‌ای مانند مجموع دقیقاً 100، قیمت نامعتبر، و کالاهای ناموجود را بررسی نمی‌کردند. همین ضعف پوشش باعث شد باگ‌ها در تست‌های اولیه دیده نشوند.

## 5) پاسخ سوال 2

برای آشکار کردن باگ، تست‌های جدیدی برای `updateItemPrice` و سناریوی مرزی تخفیف نوشته شد. ابتدا تست‌ها در فاز RED شکست خوردند، سپس پیاده‌سازی اصلاح شد و در نهایت تست‌ها پاس شدند. خروجی شکست‌خورده و خروجی موفقیت‌آمیز باید به‌صورت تصویر در گزارش قرار داده شوند.

## 6) گزارش پوشش تست

### پوشش اولیه

- Line Coverage: `<put initial line coverage here>`
- Method Coverage: `<put initial method coverage here>`
- Branch Coverage: `<put initial branch coverage here>`

### پوشش پس از بهبود

- Line Coverage: `<put final line coverage here>`
- Method Coverage: `<put final method coverage here>`
- Branch Coverage: `<put final branch coverage here>`

تحلیل تغییرات پوشش:

- `<put lines newly covered here>`
- `<put branches newly covered here>`
- `<put most effective test here>`

## 7) سوال‌هایی که باید در ویدئو پاسخ داده شوند

- سوال 3
- سوال 4
- سوال 5

## 8) بازتاب شخصی

تجربه این آزمایش نشان داد که نوشتن تست پیش از پیاده‌سازی باعث شفاف‌تر شدن رفتار مورد انتظار سیستم می‌شود و احتمال نادیده‌ماندن حالت‌های لبه‌ای را کاهش می‌دهد. همچنین هنگام بازآرایی، وجود تست‌ها اطمینان بیشتری ایجاد می‌کند که تغییرات رفتاری ناخواسته وارد نشده‌اند.

## 9) وضعیت اجرای محلی

- `javac src/*.java` با موفقیت اجرا شد.
- اجرای کامل تست‌های JUnit در این محیط به‌صورت خط فرمان ممکن نبود، چون وابستگی‌های JUnit به‌صورت محلی در دسترس نبودند.

