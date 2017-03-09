package com.fondesa.quicksavestate;

import com.fondesa.quicksavestate.annotation.SaveState;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.lang.reflect.Field;

import static com.fondesa.quicksavestate.FieldMatcher.withNames;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by antoniolig on 05/03/17.
 */
@RunWith(RobolectricTestRunner.class)
public class DefaultFieldsRetrieverTest {

    private DefaultFieldsRetriever retriever;

    @Before
    public void initRetriever() {
        retriever = new DefaultFieldsRetriever();
    }

    @After
    public void releaseRetriever() {
        retriever = null;
    }

    @Test
    public void testRetrieveZeroFields() {
        Field[] fields = retriever.getFields(ZeroFields.class);
        assertNotNull(fields);
        assertEquals(fields.length, 0);
    }

    @Test
    public void testRetrieveZeroAnnotatedFields() {
        Field[] fields = retriever.getFields(ZeroAnnotatedFields.class);
        assertNotNull(fields);
        assertEquals(fields.length, 0);
    }

    @Test
    public void testRetrievePublicAnnotatedFields() {
        Field[] fields = retriever.getFields(PublicAnnotatedFields.class);
        assertNotNull(fields);
        assertThat(fields, withNames("a", "b"));
    }

    @Test
    public void testRetrieveMixedAnnotatedFields() {
        Field[] fields = retriever.getFields(MixedPublicFields.class);
        assertNotNull(fields);
        assertThat(fields, withNames("b"));
    }

    @Test
    public void testRetrieveAnnotatedFieldsWithAllModifiers() {
        Field[] fields = retriever.getFields(AllModifiersAnnotatedFields.class);
        assertNotNull(fields);
        assertThat(fields, withNames("a", "b", "c", "d"));
    }

    @Test
    public void testRetrieveAnnotatedFieldsInheritance() {
        Field[] fields = retriever.getFields(SubClassAllModifiersAnnotatedFields.class);
        assertNotNull(fields);
        assertThat(fields, withNames("a", "b", "c", "d", "e", "f", "g", "h"));
    }

    private static class ZeroFields {
        /* empty */
    }

    private static class ZeroAnnotatedFields {
        private int a;
        protected String b;
        public double c;
        float d;
    }

    private static class PublicAnnotatedFields {
        @SaveState
        public int a;
        @SaveState
        public String b;
    }

    private static class MixedPublicFields {
        public int a;
        @SaveState
        public String b;
    }

    private static class AllModifiersAnnotatedFields {
        @SaveState
        private int a;
        @SaveState
        protected String b;
        @SaveState
        public double c;
        @SaveState
        float d;
    }

    private static class SubClassAllModifiersAnnotatedFields extends AllModifiersAnnotatedFields {
        @SaveState
        private int e;
        @SaveState
        protected String f;
        @SaveState
        public double g;
        @SaveState
        float h;
    }
}