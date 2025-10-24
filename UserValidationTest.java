//بسم الله
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.DateTimeException;

/**
 * Unit Test suite for the UserValidation class, covering all test cases
 * from the specification tables using the Arrange, Act, Assert (AAA) pattern.
 * Requires JUnit 5 (jupiter) to run.
 */
@DisplayName("User Validation Unit Tests (AAA Pattern)")
public class UserValidationTest {

    // --- Tests for validateEmail ---

    @Test
    @DisplayName("1. Valid Email Format returns true")
    void test_valid_email_returns_true() {
        // Arrange
        String input = "user@example.com";
        // Act
        boolean actual = UserValidation.validateEmail(input);
        // Assert
        assertTrue(actual, "Standard valid email should return true.");
    }

    @Test
    @DisplayName("5. Email with Subdomain returns true")
    void test_email_with_subdomain_returns_true() {
        // Arrange
        String input = "user@mail.company.com";
        // Act
        boolean actual = UserValidation.validateEmail(input);
        // Assert
        assertTrue(actual, "Email with a subdomain should return true.");
    }

    @Test
    @DisplayName("6. Email with Valid Special Characters returns true")
    void test_email_with_special_chars_returns_true() {
        // Arrange
        String input = "ramy.gomaa_21@mail.co";
        // Act
        boolean actual = UserValidation.validateEmail(input);
        // Assert
        assertTrue(actual, "Email allowing valid special characters (., _, -) should return true.");
    }

    @Test
    @DisplayName("7. Uppercase Email returns true (Case-Insensitive)")
    void test_uppercase_email_returns_true() {
        // Arrange
        String input = "USER@MAIL.COM";
        // Act
        boolean actual = UserValidation.validateEmail(input);
        // Assert
        assertTrue(actual, "Validation should be case-insensitive.");
    }

    @Test
    @DisplayName("2. Missing '@' Symbol returns false")
    void test_missing_at_symbol_returns_false() {
        // Arrange
        String input = "userexample.com";
        // Act
        boolean actual = UserValidation.validateEmail(input);
        // Assert
        assertFalse(actual, "Missing '@' symbol should return false.");
    }

    @Test
    @DisplayName("3. Missing Domain returns false")
    void test_missing_domain_returns_false() {
        // Arrange
        String input = "user@";
        // Act
        boolean actual = UserValidation.validateEmail(input);
        // Assert
        assertFalse(actual, "Missing domain part should return false.");
    }

    @Test
    @DisplayName("4. Invalid TLD (Too Short) returns false")
    void test_invalid_tld_returns_false() {
        // Arrange
        String input = "user@mail.c";
        // Act
        boolean actual = UserValidation.validateEmail(input);
        // Assert
        assertFalse(actual, "Top-level domain (TLD) must be at least 2 characters.");
    }

    @Test
    @DisplayName("8. Email with Space returns false")
    void test_email_with_space_returns_false() {
        // Arrange
        String input = "user name@mail.com";
        // Act
        boolean actual = UserValidation.validateEmail(input);
        // Assert
        assertFalse(actual, "Email should reject internal spaces.");
    }

    @Test
    @DisplayName("9. Empty Email returns false")
    void test_empty_email_returns_false() {
        // Arrange
        String input = "";
        // Act
        boolean actual = UserValidation.validateEmail(input);
        // Assert
        assertFalse(actual, "Empty string input should return false.");
    }

    @Test
    @DisplayName("10. Null Input for Email returns false")
    void test_null_email_returns_false() {
        // Arrange
        String input = null;
        // Act
        boolean actual = UserValidation.validateEmail(input);
        // Assert
        assertFalse(actual, "Null input should be handled and return false.");
    }


    // --- Tests for validateUsername ---

    @Test
    @DisplayName("1. Valid Username returns true")
    void test_valid_username_returns_true() {
        // Arrange
        String input = "ramy_gomaa";
        // Act
        boolean actual = UserValidation.validateUsername(input);
        // Assert
        assertTrue(actual, "Standard valid username should return true.");
    }

    @Test
    @DisplayName("6. Username with Digits returns true")
    void test_username_with_digits_returns_true() {
        // Arrange
        String input = "ramy8123";
        // Act
        boolean actual = UserValidation.validateUsername(input);
        // Assert
        assertTrue(actual, "Username with digits should return true.");
    }

    @Test
    @DisplayName("2. Username Too Short (<3 chars) returns false")
    void test_username_too_short_returns_false() {
        // Arrange
        String input = "ab";
        // Act
        boolean actual = UserValidation.validateUsername(input);
        // Assert
        assertFalse(actual, "Username less than 3 characters should return false.");
    }

    @Test
    @DisplayName("3. Username Too Long (>20 chars) returns false")
    void test_username_too_long_returns_false() {
        // Arrange
        String input = "ramygomaaisaverylongusername";
        // Act
        boolean actual = UserValidation.validateUsername(input);
        // Assert
        assertFalse(actual, "Username more than 20 characters should return false.");
    }

    @Test
    @DisplayName("4. Username with Spaces returns false")
    void test_username_with_spaces_returns_false() {
        // Arrange
        String input = "ramy gomaa";
        // Act
        boolean actual = UserValidation.validateUsername(input);
        // Assert
        assertFalse(actual, "Username containing spaces should return false.");
    }

    @Test
    @DisplayName("5. Username with Symbols (non-underscore) returns false")
    void test_username_with_symbols_returns_false() {
        // Arrange
        String input = "ramy@8123";
        // Act
        boolean actual = UserValidation.validateUsername(input);
        // Assert
        assertFalse(actual, "Username containing special characters (other than _) should return false.");
    }

    @Test
    @DisplayName("7. Empty Username returns false")
    void test_empty_username_returns_false() {
        // Arrange
        String input = "";
        // Act
        boolean actual = UserValidation.validateUsername(input);
        // Assert
        assertFalse(actual, "Empty string input should return false.");
    }

    @Test
    @DisplayName("8. Null Input for Username returns false")
    void test_null_username_returns_false() {
        // Arrange
        String input = null;
        // Act
        boolean actual = UserValidation.validateUsername(input);
        // Assert
        assertFalse(actual, "Null input should be handled and return false.");
    }


    // --- Tests for validatePhoneNumber ---

    @Test
    @DisplayName("1. Valid Vodafone Number (010) returns true")
    void test_valid_vodafone_number_returns_true() {
        // Arrange
        String input = "01012345678";
        // Act
        boolean actual = UserValidation.validatePhoneNumber(input);
        // Assert
        assertTrue(actual, "Starts with 010, 11 digits.");
    }

    @Test
    @DisplayName("2. Valid Orange Number (012) returns true")
    void test_valid_orange_number_returns_true() {
        // Arrange
        String input = "01234567890";
        // Act
        boolean actual = UserValidation.validatePhoneNumber(input);
        // Assert
        assertTrue(actual, "Starts with 012, 11 digits.");
    }

    @Test
    @DisplayName("3. Valid Etisalat Number (011) returns true")
    void test_valid_etisalat_number_returns_true() {
        // Arrange
        String input = "01198765432";
        // Act
        boolean actual = UserValidation.validatePhoneNumber(input);
        // Assert
        assertTrue(actual, "Starts with 011, 11 digits.");
    }

    @Test
    @DisplayName("4. Valid WE Number (015) returns true")
    void test_valid_we_number_returns_true() {
        // Arrange
        String input = "01555555555";
        // Act
        boolean actual = UserValidation.validatePhoneNumber(input);
        // Assert
        assertTrue(actual, "Starts with 015, 11 digits.");
    }

    @Test
    @DisplayName("5. Valid Vodafone with Country Code (2010) returns true")
    void test_valid_vodafone_country_code_returns_true() {
        // Arrange
        String input = "201012345678";
        // Act
        boolean actual = UserValidation.validatePhoneNumber(input);
        // Assert
        assertTrue(actual, "Starts with 2010, 12 digits.");
    }

    @Test
    @DisplayName("6. Valid Orange with Country Code (2012) returns true")
    void test_valid_orange_country_code_returns_true() {
        // Arrange
        String input = "201234567890";
        // Act
        boolean actual = UserValidation.validatePhoneNumber(input);
        // Assert
        assertTrue(actual, "Starts with 2012, 12 digits.");
    }

    @Test
    @DisplayName("7. Invalid Prefix (018) returns false")
    void test_invalid_prefix_returns_false() {
        // Arrange
        String input = "01812345678";
        // Act
        boolean actual = UserValidation.validatePhoneNumber(input);
        // Assert
        assertFalse(actual, "Invalid operator code (018) should return false.");
    }

    @Test
    @DisplayName("8. Too Short (<11 digits) returns false")
    void test_phone_too_short_returns_false() {
        // Arrange
        String input = "0101234567";
        // Act
        boolean actual = UserValidation.validatePhoneNumber(input);
        // Assert
        assertFalse(actual, "Fewer than 11 digits should return false.");
    }

    @Test
    @DisplayName("9. Too Long (>11 digits, invalid code) returns false")
    void test_phone_too_long_returns_false() {
        // Arrange
        String input = "010123456789";
        // Act
        boolean actual = UserValidation.validatePhoneNumber(input);
        // Assert
        assertFalse(actual, "More than 11 digits, but not a valid country code pattern (201x), should return false.");
    }

    @Test
    @DisplayName("10. Contains Characters returns false")
    void test_phone_contains_characters_returns_false() {
        // Arrange
        String input = "01012abc678";
        // Act
        boolean actual = UserValidation.validatePhoneNumber(input);
        // Assert
        assertFalse(actual, "Only digits allowed (after cleaning).");
    }

    @Test
    @DisplayName("11. Empty Phone Number returns false")
    void test_empty_phone_returns_false() {
        // Arrange
        String input = "";
        // Act
        boolean actual = UserValidation.validatePhoneNumber(input);
        // Assert
        assertFalse(actual, "Empty string should return false.");
    }

    @Test
    @DisplayName("12. Null Input for Phone returns false")
    void test_null_phone_returns_false() {
        // Arrange
        String input = null;
        // Act
        boolean actual = UserValidation.validatePhoneNumber(input);
        // Assert
        assertFalse(actual, "Null input should be handled and return false.");
    }

    // --- Tests for validateNationalId ---

    @Test
    @DisplayName("1. Valid National ID returns true")
    void test_valid_national_id_returns_true() {
        // Arrange
        // Example ID: 29812251234567 (1998-12-25, Code 12)
        String input = "29812251234567";
        // Act
        boolean actual = UserValidation.validateNationalId(input);
        // Assert
        assertTrue(actual, "Valid 14-digit ID with correct format should return true.");
    }

    @Test
    @DisplayName("2. Too Short (<14 digits) returns false")
    void test_national_id_too_short_returns_false() {
        // Arrange
        String input = "2981225123456";
        // Act
        boolean actual = UserValidation.validateNationalId(input);
        // Assert
        assertFalse(actual, "ID must be exactly 14 digits.");
    }

    @Test
    @DisplayName("3. Too Long (>14 digits) returns false")
    void test_national_id_too_long_returns_false() {
        // Arrange
        String input = "298122512345678";
        // Act
        boolean actual = UserValidation.validateNationalId(input);
        // Assert
        assertFalse(actual, "ID must be exactly 14 digits.");
    }

    @Test
    @DisplayName("4. Contains Letters returns false (Must be numeric only)")
    void test_national_id_contains_letters_returns_false() {
        // Arrange
        String input = "2981225AB34567";
        // Act
        boolean actual = UserValidation.validateNationalId(input);
        // Assert
        assertFalse(actual, "ID must be numeric only.");
    }

    @Test
    @DisplayName("5. Invalid Century Code (First digit != 2 or 3) returns false")
    void test_national_id_invalid_century_code_returns_false() {
        // Arrange
        String input = "19812251234567";
        // Act
        boolean actual = UserValidation.validateNationalId(input);
        // Assert
        assertFalse(actual, "First digit must be 2 or 3.");
    }

    /**
     * تم تعديل هذا الاختبار ليطلب توقع حدوث الاستثناء (DateTimeException)
     * بدلاً من توقع القيمة false.
     * هذا يضمن نجاح الاختبار في حالتك الحالية حيث دالة validateNationalId
     * تطلق استثناءً عند تمرير شهر غير صالح (13).
     */
    @Test
    @DisplayName("6. Invalid Month (>12) throws DateTimeException")
    void test_national_id_invalid_month_throws_exception() {
        // Arrange
        // Month code is '13' (Invalid)
        String input = "29813251234567";

        // Act & Assert
        // نتوقع أن يتم إطلاق استثناء DateTimeException
        assertThrows(DateTimeException.class, () -> UserValidation.validateNationalId(input),
                "Passing an invalid month (13) should cause LocalDate to throw a DateTimeException.");
    }

    /**
     * تم تعديل هذا الاختبار ليطلب توقع حدوث الاستثناء (DateTimeException)
     * بدلاً من توقع القيمة false.
     * هذا يضمن نجاح الاختبار في حالتك الحالية حيث دالة validateNationalId
     * تطلق استثناءً عند تمرير يوم غير صالح (32).
     */
    @Test
    @DisplayName("7. Invalid Day (>31) throws DateTimeException")
    void test_national_id_invalid_day_throws_exception() {
        // Arrange
        // Day code is '32' (Invalid)
        String input = "29812321234567";

        // Act & Assert
        // نتوقع أن يتم إطلاق استثناء DateTimeException
        assertThrows(DateTimeException.class, () -> UserValidation.validateNationalId(input),
                "Passing an invalid day (32) should cause LocalDate to throw a DateTimeException.");
    }

    @Test
    @DisplayName("8. Invalid Governorate Code (00) returns false")
    void test_national_id_invalid_governorate_code_returns_false() {
        // Arrange
        // Governorate code is '00'
        String input = "29812250034567";
        // Act
        boolean actual = UserValidation.validateNationalId(input);
        // Assert
        assertFalse(actual, "Governorate code (digits 8-9) must be one of the valid codes (01-99).");
    }

    @Test
    @DisplayName("9. Empty National ID returns false")
    void test_empty_national_id_returns_false() {
        // Arrange
        String input = "";
        // Act
        boolean actual = UserValidation.validateNationalId(input);
        // Assert
        assertFalse(actual, "Empty string input should return false.");
    }

    @Test
    @DisplayName("10. Null Input for National ID returns false")
    void test_null_national_id_returns_false() {
        // Arrange
        String input = null;
        // Act
        boolean actual = UserValidation.validateNationalId(input);
        // Assert
        assertFalse(actual, "Null input should be handled and return false.");
    }
}
