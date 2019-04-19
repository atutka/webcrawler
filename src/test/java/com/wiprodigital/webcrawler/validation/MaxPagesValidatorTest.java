package com.wiprodigital.webcrawler.validation;

import javax.validation.ConstraintValidatorContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@RunWith(SpringJUnit4ClassRunner.class)
public class MaxPagesValidatorTest
{
    private MaxPagesValidator validator = new MaxPagesValidator();

    private int maxPages = 100;

    @Before
    public void setUp()
    {
        ReflectionTestUtils.setField(validator, "maxPages", maxPages);
    }

    @Test
    public void testIsValidTrue()
    {
        var value = 100;
        var context = mock(ConstraintValidatorContext.class);

        boolean result = validator.isValid(value, context);

        assertThat(result).isTrue();
    }

    @Test
    public void testIsValidTrueWhenNull()
    {
        Integer value = null;
        var context = mock(ConstraintValidatorContext.class);

        boolean result = validator.isValid(value, context);

        assertThat(result).isTrue();
    }

    @Test
    public void testIsValidFalseWhenLowerThanOne()
    {
        var value = 0;
        var context = mock(ConstraintValidatorContext.class);

        boolean result = validator.isValid(value, context);

        assertThat(result).isFalse();
    }
}