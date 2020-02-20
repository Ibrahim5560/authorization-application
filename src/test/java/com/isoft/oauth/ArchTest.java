package com.isoft.oauth;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.isoft.oauth");

        noClasses()
            .that()
                .resideInAnyPackage("com.isoft.oauth.service..")
            .or()
                .resideInAnyPackage("com.isoft.oauth.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.isoft.oauth.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
