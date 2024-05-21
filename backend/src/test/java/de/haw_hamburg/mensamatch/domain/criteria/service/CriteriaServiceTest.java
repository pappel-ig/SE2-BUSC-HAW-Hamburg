package de.haw_hamburg.mensamatch.domain.criteria.service;

import de.haw_hamburg.mensamatch.domain.criteria.CriteriaRepository;
import de.haw_hamburg.mensamatch.domain.criteria.model.CriteriaSelection;
import de.haw_hamburg.mensamatch.domain.criteria.model.Criterum;
import de.haw_hamburg.mensamatch.domain.user.UserRepository;
import de.haw_hamburg.mensamatch.domain.user.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CriteriaServiceTest {

    @Mock
    private UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
    @Mock
    private CriteriaRepository mockCriteriaRepository = Mockito.mock(CriteriaRepository.class);

    private ArgumentCaptor<CriteriaSelection> argumentCaptor = ArgumentCaptor.forClass(CriteriaSelection.class);

    private CriteriaService criteriaService;

    @BeforeEach
    void setup() {
        criteriaService = new CriteriaService(mockUserRepository, mockCriteriaRepository);
    }

    @Test
    void addNewCriteriaTest() {
        final UUID id = UUID.randomUUID();
        final User value = User.builder().id(id).build();
        final CriteriaSelection criteriaSelection = CriteriaSelection.builder().criteria(new HashSet<>()).build();

        when(mockUserRepository.findUser(any())).thenReturn(Optional.ofNullable(value));
        when(mockCriteriaRepository.getCriteriaForUser(any())).thenReturn(criteriaSelection);

        criteriaService.addNewCriteria("user", Set.of("VEGAN"));

        verify(mockCriteriaRepository).getCriteriaForUser(id);
        verify(mockCriteriaRepository).updateCriteria(argumentCaptor.capture());

        assertEquals(Set.of(Criterum.VEGAN), argumentCaptor.getValue().getCriteria());
    }
}