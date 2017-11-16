package com.github.habiteria.core.domain.service.visitor;

import com.github.habiteria.core.entities.User;
import com.github.habiteria.core.entities.Visitor;
import com.github.habiteria.core.repository.VisitorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Alex Ivchenko
 */
@RunWith(SpringRunner.class)
public class VisitorServiceImplTest {
    @MockBean
    private VisitorRepository repository;

    private VisitorServiceImpl service;

    @Before
    public void setUp() throws Exception {
        service = new VisitorServiceImpl(repository);
    }

    @Test
    public void whenInvokeMethodVisitManyTimes_thenSaveOnlyOneInDay() throws Exception {
        User user = mock(User.class);

        when(repository.existsByUserAndDate(user, LocalDate.now())).
                thenReturn(false, true);

        service.visit(user);
        service.visit(user);
        service.visit(user);

        verify(repository, times(3)).existsByUserAndDate(user, LocalDate.now());
        verify(repository, times(1)).save(any(Visitor.class));
    }
}