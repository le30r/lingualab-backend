package xyz.le30r.lingualab.auth.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import xyz.le30r.lingualab.auth.entity.Invite;
import xyz.le30r.lingualab.auth.exception.InviteExpiredException;
import xyz.le30r.lingualab.auth.mapper.AuthMapper;
import xyz.le30r.lingualab.auth.repository.InviteRepository;
import xyz.le30r.lingualab.auth.service.InviteService;
import xyz.le30r.lingualab.dto.InviteDto;
import xyz.le30r.lingualab.generic.exception.ObjectNotFoundException;

import java.time.Instant;
import java.util.Random;

import static xyz.le30r.lingualab.generic.utils.DateTimeUtils.toInstant;

@Service
@RequiredArgsConstructor
public class InviteServiceImpl implements InviteService {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final InviteRepository inviteRepository;
    private final AuthMapper mapper;

    @Override
    public InviteDto createInvite(InviteDto inviteDto) {
        val invite = Invite.builder()
                .createdAt(Instant.now())
                .usages(inviteDto.getUsages())
                .expiresAt(toInstant(inviteDto.getExpiresAt()))
                .teacherId(inviteDto.getTeacherId())
                .link(generateInvitationLink(6))
                .build();
        return mapper.mapEntityToDto(
                inviteRepository.save(
                        invite
                )
        );
    }

    @Override
    public InviteDto checkInvite(String inviteLink) {
        val invite = inviteRepository.findInviteByLink(inviteLink);
        if (invite.isPresent()) {
            if (invite.get().getUsages().equals(0) || invite.get().getExpiresAt().isAfter(Instant.now())) {
               throw new InviteExpiredException();
            }
        }
        return mapper.mapEntityToDto(invite.orElseThrow(ObjectNotFoundException::new));
    }

    public static String generateInvitationLink(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(ALPHABET.length());
            sb.append(ALPHABET.charAt(index));
        }
        return sb.toString();
    }
}
