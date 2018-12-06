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
package se.kth.id1212.appserv.bank.domain;

import se.kth.id1212.appserv.bank.util.Util;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CONVERSION")
public class Conversion implements ConversionDTO {
    private static final String SEQUENCE_NAME_KEY = "SEQ_NAME";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME_KEY)
    @SequenceGenerator(name = SEQUENCE_NAME_KEY, sequenceName = "CONVERSION_SEQUENCE")

    @Column(name = "CON_FROMTO")
    private String fromto;

    @Column(name = "CON_RATE")
    private double rate;

    public Conversion(String fromto, double rate) {
        this.fromto = fromto;
        this.rate = rate;
    }

    /**
     * Required by JPA, should not be used.
     */
    protected Conversion() {
    }

    @Override
    public double getRate() {return rate;}

    /**
     * Withdraws the specified amount.
     *
     * @param amount The amount to withdraw.
     * @throws IllegalConversionException When attempting to withdraw a negative or zero amount, or if withdrawal
     *                                         would result in a negative balance.
     */
//    public void withdraw(int amount) throws IllegalConversionException {
//        if (amount <= 0) {
//            throw new IllegalConversionException("Attempt to withdraw non-positive amount: " + amount);
//        }
//        if (amount > balance) {
//            throw new IllegalConversionException(
//                "Attempt to withdraw amount greater than balance, balance: " + balance + ", amount: " + amount);
//        }
//        balance = balance - amount;
//    }

    /**
     * Deposits the specified amount.
     *
     * @param amount The amount to deposit.
     * @throws IllegalConversionException When attempting to deposit a negative or zero amount.
     */
//    public void deposit(int amount) throws IllegalConversionException {
//        if (amount <= 0) {
//            throw new IllegalConversionException("Attempt to deposit non-positive amount: " + amount);
//        }
//        balance = balance + amount;
//    }

    @Override
    public int hashCode() {
        //return Long.valueOf(acctNo).hashCode();
        return 0;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Conversion)) {
            return false;
        }
        Conversion other = (Conversion)object;
        //return this.acctNo == other.acctNo;
        return true;
    }

    @Override
    public String toString() {
        return Util.toString(this);
    }
}