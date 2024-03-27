package com.example.skiresortapi.controller.form;

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
    public static void setUpBalidation() {
        Locale.setDefault(Locale.JAPANESE);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Nested
    class SkiresortNameSizeTest {
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
        public void nameが1文字以上である時バリデーションエラーとならないこと() {
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
            var violations = validator.validate((createForm));
            // バリデーションが1つ発生することの検証
            assertThat(violations).hasSize(1);
            assertThat(violations)
                    .extracting(violation -> violation.getPropertyPath().toString(), ConstraintViolation::getPropertyPath)
                    .containsExactlyInAnyOrder(
                            tuple("name", "1 から 20 の間のサイズにしてください")
                    );
        }
    }


}
