package xyz.le30r.lingualab.auth.service;

import xyz.le30r.lingualab.auth.entity.Invite;
import xyz.le30r.lingualab.dto.InviteDto;

public interface InviteService {
    InviteDto createInvite(InviteDto inviteDto);
    InviteDto checkInvite(String inviteLink);
}
