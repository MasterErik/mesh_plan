package com.ezd.mesh.plan;

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
            .importPackages("com.ezd.mesh.plan");

        noClasses()
            .that()
            .resideInAnyPackage("com.ezd.mesh.plan.service..")
            .or()
            .resideInAnyPackage("com.ezd.mesh.plan.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.ezd.mesh.plan.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
