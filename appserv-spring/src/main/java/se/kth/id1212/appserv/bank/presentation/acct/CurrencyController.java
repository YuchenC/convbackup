/*
 * The MIT License
 *
 * Copyright 2018 Leif Lindbäck <leifl@kth.se>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package se.kth.id1212.appserv.bank.presentation.acct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import se.kth.id1212.appserv.bank.application.ConverterService;
import se.kth.id1212.appserv.bank.domain.ConversionDTO;
import se.kth.id1212.appserv.bank.presentation.error.ExceptionHandlers;

import javax.validation.Valid;

/**
 * Handles all HTTP requests to context root.
 */
@Controller
@Scope("session")
public class CurrencyController {
    static final String DEFAULT_PAGE_URL = "/";
    static final String SELECT_CON_PAGE_URL = "select-conversion";
    static final String UPDATE_RATE = "update-rate";

    private static final String CURRENT_CON_OBJ_NAME = "currentConversion";
    private static final String FIND_CONVERSION_FORM_OBJ_NAME = "findConversionForm";

    private String conversionResult;

    @Autowired
    private ConverterService service;
    private ConversionDTO currentConv;

    /**
     * No page is specified, redirect to the welcome page.
     *
     * @return A response that redirects the browser to the welcome page.
     */
    @GetMapping(DEFAULT_PAGE_URL)
    public String showDefaultView() {
        return "redirect:" + SELECT_CON_PAGE_URL;
    }

    /**
     * A get request for the currency selection page.
     *
     * @param findConversionForm   Used in the find conversion form.
     * @return The currecy to be converted selection page url.
     */
    @GetMapping("/" + SELECT_CON_PAGE_URL)
    public String showCurrencySelectionView(FindConversionForm findConversionForm) {
        return SELECT_CON_PAGE_URL;
    }

    private String showConversionResultPage(Model model) {
        if (currentConv != null) {
            model.addAttribute(CURRENT_CON_OBJ_NAME, currentConv);
        }
        return SELECT_CON_PAGE_URL;
    }

    /**
     * The find conversion form has been submitted.
     *
     * @param findConversionForm  Content of the find conversion form.
     * @param bindingResult Validation result for the find conversion form.
     * @param model         Model objects used by the currency converter page.
     * @return The converter page url if validation succeeds and exchange rate is found. Returns the error page url
     * if exchange rate is not found
     */
    @PostMapping("/" + SELECT_CON_PAGE_URL)
    public String findExchangeRate(@Valid FindConversionForm findConversionForm, BindingResult bindingResult, Model model) {

        //insertToTable();
//        System.out.println("back to find exchange rate");
//        System.out.println(bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute(FIND_CONVERSION_FORM_OBJ_NAME, new FindConversionForm());
            System.out.println("error if");
            return SELECT_CON_PAGE_URL;
        }


        String from = findConversionForm.getFrom().toUpperCase();
        int amount = findConversionForm.getNumber();
        String to = findConversionForm.getTo().toUpperCase();
        currentConv = service.findConversion(from+""+to);
        double rate = currentConv.getRate();
        findConversionForm.setConversionResult(rate*amount);
        conversionResult = findConversionForm.getConversionResult();


        System.out.println("rate = " + rate);
        System.out.println("amount = " + amount);
        System.out.println("from = " + from);
        System.out.println("to = " + to);
        System.out.println("result = " + conversionResult);


        if (currentConv == null) {
            System.out.println("currentConv = null");
            model.addAttribute(ExceptionHandlers.ERROR_TYPE_KEY, ExceptionHandlers.NO_CONVERSION_FOUND);
            model.addAttribute(ExceptionHandlers.ERROR_INFO_KEY, ExceptionHandlers.NO_CONVERSION_FOUND_INFO);
            return ExceptionHandlers.ERROR_PAGE_URL;
        }
        return showConversionResultPage(model);
    }

    public void insertToTable() {

    }
}
