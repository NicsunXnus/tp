package seedu.address.model.person;

import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

public class FieldContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;
    private final String field;

    public FieldContainsKeywordsPredicate(List<String> keywords, String field) {
        this.keywords = keywords;
        this.field = field;
    }

    @Override
    public boolean test(Person person) {
        if ("c".equals(field)) {
            return testCompany(person);
        } else if ("j".equals(field)) {
            return testJob(person);
        } else if ("t".equals(field)) {
            return testTag(person);
        } else if ("p".equals(field)) {
            return testPhone(person);
        } else if ("e".equals(field)) {
            return testEmail(person);
        } else if ("a".equals(field)) {
            return testAddress(person);
        }
        return testName(person);
    }

    private boolean testCompany(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getCompany().toString(), keyword));
    }

    private boolean testName(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword));
    }

    private boolean testJob(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getJobTitle().jobTitle, keyword));
    }

    private boolean testTag(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCaseInSet(person.getTagSet(), keyword));
    }

    private boolean testPhone(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCaseInMap((HashMap<String, ? extends Object>)
                        person.getNumbers(), keyword));
    }

    private boolean testEmail(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCaseInMap((HashMap<String, ? extends Object>)
                        person.getEmails(), keyword));
    }

    private boolean testAddress(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCaseInMap((HashMap<String, ? extends Object>)
                        person.getAddresses(), keyword));
    }
}
