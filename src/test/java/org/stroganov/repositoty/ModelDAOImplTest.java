package org.stroganov.repositoty;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.stroganov.entities.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ModelDAOImplTest {

    private static final String INPUT_PARAMETERS_CANNOT_BE_NULL = "Input parameters cannot be null";

    private static final String ERROR_MESSAGE_FOR_QUERY = "Error executing query";

    private ModelDAOImpl modelDAO;

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @Mock
    private Query<Model> query;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        modelDAO = new ModelDAOImpl(sessionFactory);
    }

    @Test
    void getByArticle() {
        // Given
        String article = "test_article";
        Model expectedModel = new Model();
        expectedModel.setArticle(article);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.createQuery("FROM Model WHERE article = :articleValue", Model.class)).thenReturn(query);
        when(query.setParameter("articleValue", article)).thenReturn(query);
        when(query.uniqueResult()).thenReturn(expectedModel);
        // When
        Model actualModel = modelDAO.getByArticle(article);
        // Then
        assertEquals(expectedModel, actualModel);
        verify(sessionFactory, times(1)).getCurrentSession();
        verify(session, times(1)).createQuery("FROM Model WHERE article = :articleValue", Model.class);
        verify(query, times(1)).setParameter("articleValue", article);
        verify(query, times(1)).uniqueResult();
    }

    @Test
    void testGetByArticleWithNullInput() {
        String article = null;
        try {
            modelDAO.getByArticle(article);
        } catch (IllegalArgumentException ex) {
            assertEquals(INPUT_PARAMETERS_CANNOT_BE_NULL, ex.getMessage());
            verifyZeroInteractions(sessionFactory);
            verifyZeroInteractions(session);
            verifyZeroInteractions(query);
        }
    }

    @Test
    void testGetByArticleWithException() {
        String article = "test_article";
        RuntimeException expectedException = new RuntimeException();
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.createQuery("FROM Model WHERE article = :articleValue", Model.class)).thenReturn(query);
        when(query.setParameter("articleValue", article)).thenReturn(query);
        when(query.uniqueResult()).thenThrow(expectedException);
        try {
            modelDAO.getByArticle(article);
        } catch (RuntimeException ex) {
            assertEquals(ERROR_MESSAGE_FOR_QUERY, ex.getMessage());
            assertEquals(expectedException, ex.getCause());
            verify(sessionFactory, times(1)).getCurrentSession();
            verify(session, times(1)).createQuery("FROM Model WHERE article = :articleValue", Model.class);
            verify(query, times(1)).setParameter("articleValue", article);
            verify(query, times(1)).uniqueResult();
        }
    }

    @Test
    void getByArticleV2() {
        // Givenng
        String parameterName = "article"; // Replace with the actual parameter name
        String parameterValue = "B36"; // Replace with the actual parameter value
        Class<Model> entityClass = Model.class; // Replace with the actual entity class
        Query<Model> query = mock(Query.class);
        when(sessionFactory.getCurrentSession()).thenReturn(mock(Session.class));
        when(sessionFactory.getCurrentSession().createQuery(anyString(), eq(entityClass))).thenReturn(query);
        when(query.setParameter(eq("paramValue"), eq(parameterValue))).thenReturn(query);
        Model expectedEntity = new Model();
        when(query.uniqueResult()).thenReturn(expectedEntity);
        // When
        Model entity = modelDAO.getEntityFromDBByParam(parameterName, parameterValue, entityClass);
        // Then
        assertEquals(expectedEntity, entity, "Entity should match the expected entity");
    }
}
