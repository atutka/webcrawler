package com.wiprodigital.webcrawler.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Value;

public class MaxPagesValidator implements ConstraintValidator<MaxPages, Integer>
{
    @Value("${webcrawler.maxpages}")
    private int maxPages;

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context)
    {
        if(value == null)
        {
            return true;
        }
        if(value < 1)
        {
            return false;
        }
        return value <= maxPages;
    }
}
