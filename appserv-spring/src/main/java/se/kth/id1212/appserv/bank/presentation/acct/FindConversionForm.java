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

import se.kth.id1212.appserv.bank.util.Util;

import javax.validation.constraints.NotNull;

/**
 * A form bean for the search account form.
 */
class FindConversionForm {
    @NotNull(message = "Please specify account number")
    private int number;
    private String from;
    private String to;
    private String conversionResult;

    /**
     * @return The amount of the currency to be converted from.
     */
    public int getNumber() {
        return number;
    }

    /**
     * @param number The number of the searched account.
     */
    public void setNumber(int number) {
        this.number = number;
    }

    public String getFrom() {return from; }
    public void setFrom(String from) {this.from = from;}

    public String getTo() {return to;}
    public void setTo(String to) {this.to = to;}

    public void setConversionResult(double conversionResult) {this.conversionResult = conversionResult+" " + this.to;}
    public String getConversionResult() {return conversionResult;}

    @Override
    public String toString() {
        return Util.toString(this);
    }
}
