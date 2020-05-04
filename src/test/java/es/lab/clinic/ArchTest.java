package es.lab.clinic;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("es.lab.clinic");

        noClasses()
            .that()
            .resideInAnyPackage("es.lab.clinic.service..")
            .or()
            .resideInAnyPackage("es.lab.clinic.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..es.lab.clinic.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
