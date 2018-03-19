package seedu.address.ui;

import static seedu.address.testutil.EventsUtil.postNow;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.ui.testutil.GuiTestAssert.assertBrowserDisplaysPerson;

import org.junit.Before;
import org.junit.Test;

import guitests.guihandles.BrowserPanelHandle;

import seedu.address.commons.events.ui.PersonPanelSelectionChangedEvent;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class BrowserPanelTest extends GuiUnitTest {
    private PersonPanelSelectionChangedEvent selectionChangedEventStubStudent;
    private PersonPanelSelectionChangedEvent selectionChangedEventStubTutor;

    private BrowserPanel browserPanel;
    private BrowserPanelHandle browserPanelHandle;

    @Before
    public void setUp() {
        selectionChangedEventStubStudent = new PersonPanelSelectionChangedEvent(new PersonCard(ALICE, 0));
        selectionChangedEventStubTutor = new PersonPanelSelectionChangedEvent(new PersonCard(BENSON, 0));

        guiRobot.interact(() -> browserPanel = new BrowserPanel());
        uiPartRule.setUiPart(browserPanel);

        browserPanelHandle = new BrowserPanelHandle(browserPanel.getRoot());
    }

    @Test
    public void display() {
        // student
        Person student = new PersonBuilder().build();
        postNow(selectionChangedEventStubStudent);
        assertBrowserDisplay(student);

        // tutor
        Person tutor = new PersonBuilder().withName("Benson Meier")
                .withAddress("311, Clementi Ave 2, #02-25").withEmail("johnd@example.com").withPhone("98765432")
                .withPrice("150").withSubject("English").withStatus("NotMatched").withLevel("Secondary 2")
                .withTags("Tutor").build();
        postNow(selectionChangedEventStubTutor);
        assertBrowserDisplay(tutor);
    }

    /**
     * Asserts that {@code browserPanel} displays the details of {@code expectedPerson} correctly.
     */
    private void assertBrowserDisplay(Person expectedPerson) {
        guiRobot.pauseForHuman();

        // verify person details are displayed correctly
        assertBrowserDisplaysPerson(expectedPerson, browserPanelHandle);
    }
}
