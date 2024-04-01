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


class SkiresortPatchFormTest {

    private static Validator validator;

    @BeforeAll
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Nested
    class NullTest {

        @Test
        public void nameとareaとimpressionの全てがnullの時にバリデーションエラーとなること() {
            SkiresortPatchForm patchForm = new SkiresortPatchForm(null, null, null);

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(patchForm);
            assertThat(violations).hasSize(1)
                    .extracting(violation -> violation.getPropertyPath().toString(), ConstraintViolation::getMessage)
                    .containsExactlyInAnyOrder(
                            tuple("nameOrAreaOrImpression", "name, area, impressionのいずれかを入力してください")
                    );
        }

        @Test
        public void nameのみがnullの時にバリデーションエラーとならないこと() {
            SkiresortPatchForm patchForm = new SkiresortPatchForm(null, "Canada", "Ski the World Heritage Site of the Canadian Rockies");

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(patchForm);
            assertThat(violations).isEmpty();
        }

        @Test
        public void areaのみがnullの時にバリデーションエラーとならないこと() {
            SkiresortPatchForm patchForm = new SkiresortPatchForm("Lake Louise", null, "Ski the World Heritage Site of the Canadian Rockies");

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(patchForm);
            assertThat(violations).isEmpty();
        }

        @Test
        public void impressionのみがnullの時にバリデーションエラーとならないこと() {
            SkiresortPatchForm patchForm = new SkiresortPatchForm("Lake Louise", "Canada", null);

            Set<ConstraintViolation<SkiresortPatchForm>> violations = validator.validate(patchForm);
            assertThat(violations).isEmpty();
        }
    }

}
