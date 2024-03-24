package com.example.skiresortapi.validation;

import com.example.skiresortapi.controller.form.SkiresortCreateForm;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

class SkiresortCreateFormTest {

    private static Validator validator;

    // 一番最初に一度だけ実行される
    @BeforeAll
    public static void setUpValidator() {
        Locale.setDefault(Locale.JAPANESE);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Nested
    class NameSizeTest {

        @Test
        public void nameが1文字未満である時バリデーションエラーとなること() {

            SkiresortCreateForm createForm = new SkiresortCreateForm("", "Canada", "The scenery was very beautiful");
            var violations = validator.validate(createForm);
            // バリデーションが2つ発生することの検証
            assertThat(violations).hasSize(2);
            assertThat(violations)
                    .extracting(violation -> violation.getPropertyPath().toString(), ConstraintViolation::getMessage)
                    .containsExactlyInAnyOrder(
                            tuple("name", "空白は許可されていません"),
                            tuple("name", "1 から 20 の間のサイズにしてください")
                    );
        }

        @Test
        public void nameが1文字である時バリデーションエラーとならないこと() {
            SkiresortCreateForm createForm = new SkiresortCreateForm("a", "Canada", "The scenery was very beautiful");
            var violations = validator.validate(createForm);
            assertThat(violations).isEmpty();
        }

        @Test
        public void nameが20文字である時バリデーションエラーとならないこと() {
            SkiresortCreateForm createForm = new SkiresortCreateForm("aaaaaaaaaaaaaaaaaaaa", "Canada", "The scenery was very beautiful");
            var violations = validator.validate(createForm);
            assertThat(violations).isEmpty();
        }

        @Test
        public void nameが21文字である時バリデーションエラーとなること() {
            SkiresortCreateForm createForm = new SkiresortCreateForm("aaaaaaaaaaaaaaaaaaaaa", "Canada", "The scenery was very beautiful");
            var violations = validator.validate(createForm);
            // バリデーションエラーが1つ発生することの検証
            assertThat(violations).hasSize(1);
            assertThat(violations)
                    .extracting(violation -> violation.getPropertyPath().toString(), ConstraintViolation::getMessage)
                    .containsExactlyInAnyOrder(tuple("name", "1 から 20 の間のサイズにしてください"));
        }
    }

    @Nested
    class NameNotBlankTest {

        @Test
        public void nameが半角ブランクである時バリデーションエラーとなること() {
            SkiresortCreateForm createForm = new SkiresortCreateForm(" ", "Canada", "The scenery was very beautiful");
            var violations = validator.validate(createForm);
            assertThat(violations).hasSize(1);
            assertThat(violations)
                    .extracting(violation -> violation.getPropertyPath().toString(), ConstraintViolation::getMessage)
                    .containsExactlyInAnyOrder(tuple("name", "空白は許可されていません"));
        }

        @Test
        public void nameがnullである時バリデーションエラーとなること() {
            SkiresortCreateForm createForm = new SkiresortCreateForm(null, "Canada", "The scenery was very beautiful");
            var violations = validator.validate(createForm);
            assertThat(violations).hasSize(1);
            assertThat(violations)
                    .extracting(violation -> violation.getPropertyPath().toString(), ConstraintViolation::getMessage)
                    .containsExactlyInAnyOrder(tuple("name", "空白は許可されていません"));
        }

        @Test
        public void nameが全角ブランクである時バリデーションエラーとならないこと() {
            SkiresortCreateForm createForm = new SkiresortCreateForm("　", "Canada", "The scenery was very beautiful");
            var violations = validator.validate(createForm);
            assertThat(violations).isEmpty();
        }
    }

    @Nested
    class AreaSizeTest {

        @Test
        public void areaが1文字未満である時バリデーションエラーとなること() {
            SkiresortCreateForm createForm = new SkiresortCreateForm("Remarkables", "", "Easy to slip even for beginners");
            var violations = validator.validate(createForm);
            assertThat(violations).hasSize(2);
            assertThat(violations)
                    .extracting(violation -> violation.getPropertyPath().toString(), ConstraintViolation::getMessage)
                    // 特定の条件が順不要で一致することの検証
                    .containsExactlyInAnyOrder(
                            tuple("area", "空白は許可されていません"),
                            tuple("area", "1 から 20 の間のサイズにしてください")
                    );
        }

        @Test
        public void areaが1文字である時バリデーションエラーとならないこと() {
            SkiresortCreateForm createForm = new SkiresortCreateForm("Remarkables", "a", "Easy to slip even for beginners");
            var violations = validator.validate(createForm);
            // エラーなしであることの検証
            assertThat(violations).isEmpty();
        }

        @Test
        public void areaが20文字である時バリデーションエラーとならないこと() {
            SkiresortCreateForm createForm = new SkiresortCreateForm("Remarkables", "12345678901234567890", "Easy to slip even for beginners");
            var violations = validator.validate(createForm);
            // エラーなしであることの検証
            assertThat(violations).isEmpty();
        }

        @Test
        public void areaが21文字である時バリデーションエラーとなること() {
            SkiresortCreateForm createForm = new SkiresortCreateForm("Remarkable", "123456789012345678901", "Easy to slip even for beginners");
            var violations = validator.validate(createForm);
            assertThat(violations).hasSize(1)
                    .extracting(violation -> violation.getPropertyPath().toString(), ConstraintViolation::getMessage)
                    .containsExactlyInAnyOrder(
                            tuple("area", "1 から 20 の間のサイズにしてください")
                    );
        }
    }

    @Nested
    class AreaNotBlankTest {

        @Test
        public void areaが半角ブランクである時バリデーションエラーとなること() {
            SkiresortCreateForm createForm = new SkiresortCreateForm("Remarkable", " ", "Easy to slip even for beginners");
            var violations = validator.validate(createForm);
            assertThat(violations).hasSize(1);
            assertThat(violations)
                    .extracting(violation -> violation.getPropertyPath().toString(), ConstraintViolation::getMessage)
                    .containsExactlyInAnyOrder(tuple("area", "空白は許可されていません"));
        }

        @Test
        public void areaがnullである時バリデーションエラーとなること() {
            SkiresortCreateForm createForm = new SkiresortCreateForm("Remarkable", null, "Easy to slip even for beginners");
            var violations = validator.validate(createForm);
            assertThat(violations).hasSize(1);
            assertThat(violations)
                    .extracting(violation -> violation.getPropertyPath().toString(), ConstraintViolation::getMessage)
                    .containsExactlyInAnyOrder(tuple("area", "空白は許可されていません"));
        }

        @Test
        public void areaが全角ブランクである時バリデーションエラーとならないこと() {
            SkiresortCreateForm createForm = new SkiresortCreateForm("Remarkable", "　", "Easy to slip even for beginners");
            var violations = validator.validate(createForm);
            assertThat(violations).isEmpty();
        }
    }

    @Nested
    class ImpressionSizeTest {

        @Test
        public void impressionが1文字未満である時バリデーションエラーとなること() {
            SkiresortCreateForm createForm = new SkiresortCreateForm("Mt.Hoot", "USA", "");
            var violations = validator.validate(createForm);
            assertThat(violations).hasSize(2);
            assertThat(violations)
                    .extracting(violation -> violation.getPropertyPath().toString(), ConstraintViolation::getMessage)
                    .containsExactlyInAnyOrder(
                            tuple("impression", "空白は許可されていません"),
                            tuple("impression", "1 から 50 の間のサイズにしてください")
                    );
        }

        @Test
        public void impressionが1文字である時バリデーションエラーとならないこと() {
            SkiresortCreateForm createForm = new SkiresortCreateForm("Mt.Hoot", "USA", "1");
            var violations = validator.validate(createForm);
            assertThat(violations).isEmpty();
        }

        @Test
        public void impressionが50文字である時バリデーションエラーとならないこと() {
            SkiresortCreateForm createForm = new SkiresortCreateForm("Mt.Hoot", "USA", "12345678901234567890123456789012345678901234567890");
            var violations = validator.validate(createForm);
            assertThat(violations).isEmpty();
        }

        @Test
        public void impressionが51文字である時バリデーションエラーとなること() {
            SkiresortCreateForm createForm = new SkiresortCreateForm("Mt.Hoot", "USA", "123456789012345678901234567890123456789012345678901");
            var violations = validator.validate(createForm);
            assertThat(violations).hasSize(1);
            assertThat(violations)
                    .extracting(violation -> violation.getPropertyPath().toString(), ConstraintViolation::getMessage)
                    .containsExactlyInAnyOrder(tuple("impression", "1 から 50 の間のサイズにしてください"));
        }
    }

    @Nested
    class ImpressionNotBlankTest {

        @Test
        public void impresstionが半角ブランクである時バリデーションエラーとなること() {
            SkiresortCreateForm createForm = new SkiresortCreateForm("Mt.Hoot", "USA", " ");
            var violations = validator.validate(createForm);
            assertThat(violations).hasSize(1)
                    .extracting(violation -> violation.getPropertyPath().toString(), ConstraintViolation::getMessage)
                    .containsExactlyInAnyOrder(tuple("impression", "空白は許可されていません"));
        }

        @Test
        public void impressionがnullである時バリデーションエラーとなること() {
            SkiresortCreateForm createForm = new SkiresortCreateForm("Mt.Hoot", "USA", null);
            var violations = validator.validate(createForm);
            assertThat(violations).hasSize(1)
                    .extracting(violation -> violation.getPropertyPath().toString(), ConstraintViolation::getMessage)
                    .containsExactlyInAnyOrder(tuple("impression", "空白は許可されていません"));
        }

        @Test
        public void impressionが全角ブランクである時バリデーションエラーとならないこと() {
            SkiresortCreateForm createForm = new SkiresortCreateForm("Mt.Hoot", "USA", "　");
            var violations = validator.validate(createForm);
            assertThat(violations).isEmpty();
        }
    }
}
