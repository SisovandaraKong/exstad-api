package co.istad.exstadapi.features.applicantLetter;

import co.istad.exstadapi.features.applicantLetter.dto.KhmerDate;

import java.util.Date;
import java.util.Calendar;
import java.util.Map;

public class KhmerDateConverter {

    private static final Map<Integer, String> KHMER_MONTHS = Map.ofEntries(
            Map.entry(1, "មករា"),
            Map.entry(2, "កុម្ភៈ"),
            Map.entry(3, "មីនា"),
            Map.entry(4, "មេសា"),
            Map.entry(5, "ឧសភា"),
            Map.entry(6, "មិថុនា"),
            Map.entry(7, "កក្កដា"),
            Map.entry(8, "សីហា"),
            Map.entry(9, "កញ្ញា"),
            Map.entry(10, "តុលា"),
            Map.entry(11, "វិច្ឆិកា"),
            Map.entry(12, "ធ្នូ")
    );

    private static final char[] KHMER_DIGITS = {'០','១','២','៣','៤','៥','៦','៧','៨','៩'};

    public static String toKhmerDigits(int number) {
        String numStr = String.valueOf(number);
        StringBuilder khmer = new StringBuilder();
        for (char c : numStr.toCharArray()) {
            if (Character.isDigit(c)) {
                khmer.append(KHMER_DIGITS[c - '0']);
            } else {
                khmer.append(c);
            }
        }
        return khmer.toString();
    }

    public static KhmerDate convertToKhmerDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH) + 1; // MONTH is 0-based
        int year = cal.get(Calendar.YEAR);

        return new KhmerDate(
                toKhmerDigits(day),
                KHMER_MONTHS.get(month),
                toKhmerDigits(year)
        );
    }
}

