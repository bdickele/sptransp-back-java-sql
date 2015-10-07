package org.bdickele.sptransp.service;

import org.bdickele.sptransp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        int length = fullName.length();
        String prefix = fullName.substring(0, length < UID_PREFIX_LENGTH ? length : UID_PREFIX_LENGTH);

        List<String> uidsWithSamePrefix = repository.findUidsStartingWith(prefix);

        //TODO

        List<Integer> suffixes = new ArrayList<>();

        return null;
    }

    /**
     * If prefix is equal to "john" and :<ul>
     *     <li>uid = "john12", then method returs Some(12)</li>
     *     <li>uid = "johndoe12", then method returs Optional.empty</li>
     * </ul>
     * @param uid
     * @param prefix
     * @return
     */
    public static Optional<Integer> extractSuffixNumberFromUid(String uid, String prefix) {
        String suffix = uid.substring(prefix.length());
        try {
            Integer i = Integer.valueOf(suffix);
            return Optional.of(i);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
