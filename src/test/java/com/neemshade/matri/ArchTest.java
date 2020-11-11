package com.neemshade.matri;

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
            .importPackages("com.neemshade.matri");

        noClasses()
            .that()
            .resideInAnyPackage("com.neemshade.matri.service..")
            .or()
            .resideInAnyPackage("com.neemshade.matri.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.neemshade.matri.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
