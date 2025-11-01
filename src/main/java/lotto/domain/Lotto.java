package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static lotto.util.LottoConstants.*;

public class Lotto {
    private final List<Integer> numbers;

    private static final String ERROR_SIZE = "로또 번호는 " + LOTTO_NUMBER_COUNT + "개여야 합니다.";
    private static final String ERROR_DUPLICATE = "로또 번호는 중복될 수 없습니다.";
    private static final String ERROR_RANGE = "로또 번호는 " + LOTTO_NUMBER_MIN + "에서 " + LOTTO_NUMBER_MAX + " 사이여야 합니다.";

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
        validateRange(numbers);
    }

    private static void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ERROR_SIZE);
        }
    }

    private static void validateDuplicate(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (numbers.size() != uniqueNumbers.size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATE);
        }
    }

    private static void validateRange(List<Integer> numbers) {
        for (Integer number : numbers) {
            if (number < LOTTO_NUMBER_MIN || number > LOTTO_NUMBER_MAX) {
                throw new IllegalArgumentException(ERROR_RANGE);
            }
        }
    }

    public boolean has(int number) {
        return numbers.contains(number);
    }

    public int calculateMatchCount(Lotto lotto) {
        int count = 0;
        for (Integer number : numbers) {
            if (lotto.has(number)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
