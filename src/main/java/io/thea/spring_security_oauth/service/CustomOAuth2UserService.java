package io.thea.spring_security_oauth.service;

import io.thea.spring_security_oauth.entity.User;
import io.thea.spring_security_oauth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String oauthProvider = userRequest.getClientRegistration().getRegistrationId();
        String oauthProviderId = oAuth2User.getName();
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        String profilePicture = oAuth2User.getAttribute("picture");

        Optional<User> existingUser = userRepository.findByOauthProviderAndOauthProviderId(oauthProvider, oauthProviderId);

        User user;
        if (existingUser.isPresent()) {
            user = existingUser.get();
            user.setEmail(email);
            user.setName(name);
            user.setProfilePicture(profilePicture);
            user.setUpdatedAt(LocalDateTime.now());
        } else {
            user = new User();
            user.setOauthProvider(oauthProvider);
            user.setOauthProviderId(oauthProviderId);
            user.setEmail(email);
            user.setName(name);
            user.setProfilePicture(profilePicture);
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());
        }

        userRepository.save(user);

        return new CustomOAuth2User(oAuth2User);
    }
}
