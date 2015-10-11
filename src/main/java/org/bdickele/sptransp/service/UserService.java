package org.bdickele.sptransp.service;

import org.bdickele.sptransp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Created by Bertrand DICKELE
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class UserService extends AbstractService {

    private static final int UID_PREFIX_LENGTH = 6;

    @Autowired
    private UserRepository repository;

    /**
     * Generates a brand new UID based on a given full name
     * @param fullName
     * @return New UID
     */
    public String generateUid(String fullName) {
        String fullNameUsed = fullName.replaceAll(" ", "").toLowerCase();
        int length = fullNameUsed.length();
        String prefix = fullNameUsed.substring(0, length < UID_PREFIX_LENGTH ? length : UID_PREFIX_LENGTH);

        // If prefix is "john" that repository method may return "john1", "john2" and "johndoe3"
        // That way we can perform a first filter
        List<String> uidsWithSamePrefix = repository.findUidsStartingWith(prefix);

        Optional<Integer> maxIndex = getMaxIndexOfUidsWithSamePrefix(uidsWithSamePrefix, prefix);

        return prefix + (maxIndex.orElse(0) + 1);
    }

    /**
     * If prefix is equal to "john" and :<ul>
     *     <li>uid = "john12", then method returs Optional.of(12)</li>
     *     <li>uid = "johndoe12", then method returns Optional.empty</li>
     * </ul>
     * @param uid
     * @param prefix
     * @return
     */
    public static Optional<Integer> extractSuffixNumberFromUid(String uid, String prefix) {
        if (!uid.startsWith(prefix)) return Optional.empty();

        try {
            String suffix = uid.substring(prefix.length());
            Integer i = Integer.valueOf(suffix);
            return Optional.of(i);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    /**
     * Highest index of uids with exactly the same prefix./ For instance if prefix = "john", and list is
     * made of "john1", "john2" and "johnny5", then highest index is 2 (johndoe has not exactly the same prefix)
     * @param uids List of UIDs, for instance john3
     * @param prefix Prefix of UID (what is before the number)
     * @return Optional with max index. Optional is empty if there is no uid with exactly the same prefix.
     */
    public static Optional<Integer> getMaxIndexOfUidsWithSamePrefix(List<String> uids, String prefix) {
        return uids.stream()
                .map(u -> extractSuffixNumberFromUid(u, prefix))
                .filter(o -> o.isPresent())
                .map(Optional::get)
                .max(Comparator.<Integer>naturalOrder());
    }
}
