package com.example.skiresortapi.controller.form;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

/**
 * スキーリゾート更新のテスト
 */
class SkiresortPatchFormTest {

    private static Validator validator;

    // 一番最初に一度だけ実行される

    /**
     * テスト実行前にバリデータをセットアップする
     */
    @BeforeAll
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * null値テスト
     */
    @Nested
    class NullTest {

        @Test
        public void nameとareaとimpressionの全てがnullの時バリデーションエラーとなること() {
            SkiresortPatchForm patchForm = new SkiresortPatchForm(null, null, null);

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(patchForm);
            assertThat(violations).hasSize(1);
            // 制約違反(ConstraintViolation)情報でどのプロパティに関連しているか、エラーメッセージが何かを検証する
            assertThat(violations)
                    .extracting(violation -> violation.getPropertyPath().toString(), ConstraintViolation::getMessage)
                    .containsExactlyInAnyOrder(
                            tuple("nameOrAreaOrImpression", "name, area, impressionのいずれかを入力してください")
                    );
        }

        @Test
        public void nameのみがnullの時バリデーションエラーとならないこと() {
            SkiresortPatchForm patchForm = new SkiresortPatchForm(null, "Canada", "Ski the World Heritage Site of the Canadian Rockies");

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(patchForm);
            assertThat(violations).isEmpty();
        }

        @Test
        public void areaのみがnullの時バリデーションエラーとならないこと() {
            SkiresortPatchForm patchForm = new SkiresortPatchForm("Lake Louise", null, "Ski the World Heritage Site of the Canadian Rockies");

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(patchForm);
            assertThat(violations).isEmpty();
        }

        @Test
        public void impressionのみがnullの時バリデーションエラーとならないこと() {
            SkiresortPatchForm patchForm = new SkiresortPatchForm("Lake Louise", "Canada", null);

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(patchForm);
            assertThat(violations).isEmpty();
        }
    }

    /**
     * 空文字テスト
     */
    @Nested
    class EmptyStringTest {

        @Test
        public void nameとareaとimpressionの全て空文字の時バリデーションエラーとなること() {
            SkiresortPatchForm patchForm = new SkiresortPatchForm("", "", "");

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(patchForm);
            assertThat(violations).hasSize(1);
            // 制約違反(ConstraintViolation)情報でどのプロパティに関連しているか、エラーメッセージが何かを検証する
            assertThat(violations)
                    .extracting(violation -> violation.getPropertyPath().toString(), ConstraintViolation::getMessage)
                    .containsExactlyInAnyOrder(
                            tuple("nameOrAreaOrImpression", "name, area, impressionのいずれかを入力してください")
                    );
        }

        @Test
        public void nameのみが空文字の時バリデーションエラーとならないこと() {
            SkiresortPatchForm patchForm = new SkiresortPatchForm("", "Canada", "Ski the World Heritage Site of the Canadian Rockies");

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(patchForm);
            assertThat(violations).isEmpty();
        }

        @Test
        public void areaのみが空文字の時バリデーションエラーとならないこと() {
            SkiresortPatchForm patchForm = new SkiresortPatchForm("Lake Louise", "", "Ski the World Heritage Site of the Canadian Rockies");

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(patchForm);
            assertThat(violations).isEmpty();
        }


        @Test
        public void impressionのみが空文字の時バリデーションエラーとならないこと() {
            SkiresortPatchForm patchForm = new SkiresortPatchForm("Lake Louise", "Canada", "");

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(patchForm);
            assertThat(violations).isEmpty();
        }
    }

    /**
     * 半角スペーステスト
     */
    @Nested
    class BlankTest {

        @Test
        public void nameとareaとimpressionが全て半角スペースの時バリデーションエラーとなること() {
            SkiresortPatchForm patchForm = new SkiresortPatchForm(" ", " ", " ");

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(patchForm);
            assertThat(violations).hasSize(1);
            assertThat(violations)
                    .extracting(violation -> violation.getPropertyPath().toString(), ConstraintViolation::getMessage)
                    .containsExactlyInAnyOrder(
                            tuple("nameOrAreaOrImpression", "name, area, impressionのいずれかを入力してください")
                    );
        }

        @Test
        void nameのみが半角スペースの時バリデーションエラーとならないこと() {
            SkiresortPatchForm patchForm = new SkiresortPatchForm(" ", "Canada", "Ski the World Heritage Site of the Canadian Rockies");

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(patchForm);
            assertThat(violations).isEmpty();
        }

        @Test
        void areaのみが半角スペースの時バリデーションエラーとならないこと() {
            SkiresortPatchForm patchForm = new SkiresortPatchForm("Lake Louise", " ", "Ski the World Heritage Site of the Canadian Rockies");

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(patchForm);
            assertThat(violations).isEmpty();
        }

        @Test
        void impressionのみが半角スペースの時バリデーションエラーとならないこと() {
            SkiresortPatchForm patchForm = new SkiresortPatchForm("Lake Louise", "Canada", "");

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(patchForm);
            assertThat(violations).isEmpty();
        }
    }

    /**
     * 正常値テスト
     */
    @Nested
    class OkPatternTest {

        @Test
        void name以外がnullと空文字の時バリデーションエラーとならないこと() {
            SkiresortPatchForm patchForm = new SkiresortPatchForm("Lake Louise", null, "");

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(patchForm);
            assertThat(violations).isEmpty();
        }

        @Test
        void area以外がnullと半角スペースの時バリデーションエラーとならないこと() {
            SkiresortPatchForm patchForm = new SkiresortPatchForm(null, "Canada", " ");

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(patchForm);
            assertThat(violations).isEmpty();
        }

        @Test
        void imperssion以外が空文字と半角スペースの時バリデーションエラーとならないこと() {
            SkiresortPatchForm patchForm = new SkiresortPatchForm("", " ", "Ski the World Heritage Site of the Canadian Rockies");

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(patchForm);
            assertThat(violations).isEmpty();
        }
    }
}
