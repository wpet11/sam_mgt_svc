package com.csf.basedata.sammgt.service;

import com.csf.basedata.sammgt.domain.entity.clerk.Clerk;

import java.util.List;

public interface ClerkService {
    List<Clerk> getClerk();

    Integer updateClerk(Clerk clerk);
}
