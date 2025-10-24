//بسم الله
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
// تم حذف Imports غير المستخدمة لتنظيف الكود

/**
 * فئة مساعدة لوظائف التحقق الشائعة لمدخلات المستخدمين.
 * تحتوي على دوال للتحقق من البريد الإلكتروني واسم المستخدم ورقم الهاتف والرقم القومي المصري.
 */
public class UserValidation {

    // مثال عن أكواد محافظات مصر المفترض أنها جزء من رقم البطاقة الشخصية
    private static final Set<String> GOVERNORATE_CODES = new HashSet<>(Arrays.asList(
            "01", "02", "03", "04", "11", "12", "13", "14", "15", "16", "17",
            "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
            "30", "31", "32", "33", "34", "35", "88", "99"
    ));

    // دالة التحقق من صحة البريد الإلكتروني
    public static boolean validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        // تنظيم مدخلات البريد الإلكتروني الصحيحة
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        // للتحقق من تلائم أو تناسب البريد الإلكتروني مع البنية المخصصة له
        return Pattern.matches(emailRegex, email);
    }

    // دالة التحقق من صحة اسم المستخدم
    public static boolean validateUsername(String username) {
        if (username == null || username.isEmpty()) {
            return false;
        }
        String usernameRegex = "^[a-zA-Z0-9_]{3,20}$";
        return Pattern.matches(usernameRegex, username);
    }

    // دالة التحقق من صحة رقم الهاتف
    public static boolean validatePhoneNumber(String phone) {
        if (phone == null || phone.isEmpty()) {
            return false;
        }

        // حذف المسافات والرموز من الحقل
        // تم توسيع cleanPhone لحذف أي رمز غير رقمي لزيادة المرونة
        String cleanPhone = phone.replaceAll("[^0-9]", "");

        // التحقق من أن الرقم إما 11 رقماً يبدأ بـ 01[0125] أو 12 رقماً يبدأ بـ 201[0125]
        String phoneRegex = "^(01[0125]\\d{8}|201[0125]\\d{8})$";
        return Pattern.matches(phoneRegex, cleanPhone);
    }

    // دالة التحقق من صحة رقم البطاقة القومية المصرية (14 رقماً)
    public static boolean validateNationalId(String nationalId) {
        if (nationalId == null) {
            return false;
        }

        // 1. للتحقق من الأساسيات: يجب أن يكون 14 رقماً فقط
        if (!Pattern.matches("^\\d{14}$", nationalId)) {
            return false;
        }

        // 2. تعريف القرن والتحقق منه (يجب أن يكون '2' أو '3')
        char centuryDigit = nationalId.charAt(0);
        String yearCode = nationalId.substring(1, 3);
        String monthCode = nationalId.substring(3, 5);
        String dayCode = nationalId.substring(5, 7);
        String governorateCode = nationalId.substring(7, 9);

        if (centuryDigit != '2' && centuryDigit != '3') {
            return false;
        }

        int fullYear;
        if (centuryDigit == '2') {
            fullYear = 1900 + Integer.parseInt(yearCode);
        } else { // centuryDigit == '3'
            fullYear = 2000 + Integer.parseInt(yearCode);
        }

        // 3. التحقق من صحة التاريخ
        try {
            int month = Integer.parseInt(monthCode);
            int day = Integer.parseInt(dayCode);

            // محاولة إنشاء تاريخ (تتحقق تلقائياً من صحة الشهر واليوم والسنة الكبيسة)
            LocalDate dob = LocalDate.of(fullYear, month, day);

            // التحقق مما إذا كان التاريخ في المستقبل
            if (dob.isAfter(LocalDate.now())) {
                return false;
            }
        } catch (DateTimeParseException | NumberFormatException e) {
            // يتم إرجاع false عند فشل التحقق من التاريخ (مثل شهر 13 أو يوم 32)
            return false;
        }

        // 4. فحص رمز المحافظة
        if (!GOVERNORATE_CODES.contains(governorateCode)) {
            return false;
        }

        return true;
    }
}

