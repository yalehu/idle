package tsp.program.type.impl.objc;

import org.antlr.v4.runtime.tree.ParseTree;
import tsp.feature.plantuml.classes.element.ClassesDiagramElement;
import tsp.feature.plantuml.classes.element.impl.CDEClassImpl;
import tsp.feature.plantuml.classes.element.item.CDItemTag;
import tsp.feature.plantuml.classes.element.item.impl.CDMethodImpl;
import tsp.feature.plantuml.classes.relation.ClassesDiagramRelation;
import tsp.feature.plantuml.classes.relation.impl.CDRExtendImpl;
import tsp.feature.plantuml.classes.relation.impl.CDRImplementImpl;
import tsp.gen.ObjectiveCParser;
import tsp.program.method.MethodElement;
import tsp.program.method.impl.objc.ObjcMethodElementImpl;
import tsp.program.type.AbstractTypeElement;
import tsp.program.type.ClassElement;
import tsp.program.type.InterfaceElement;
import tsp.program.type.TypeTag;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ObjcClassElementImpl extends AbstractTypeElement implements ClassElement {

    ObjectiveCParser.ClassInterfaceContext classInterfaceContext;
    ObjectiveCParser.ClassImplementationContext classImplementationContext;

    ObjectiveCParser.CategoryInterfaceContext anonymousCategoryInterfaceContexts;
    Set<ObjectiveCParser.CategoryInterfaceContext> categoryInterfaceContexts;
    Set<ObjectiveCParser.CategoryImplementationContext> categoryImplementationContexts;



    Set<ParseTree> tree;

    public ObjcClassElementImpl(String name) {
        super(name);
        this.categoryImplementationContexts = new HashSet<>();
        this.categoryInterfaceContexts = new HashSet<>();
    }

    public ObjcClassElementImpl(String name, Set<TypeTag> tags, Set<ParseTree> tree) {
        super(name, tags);
        this.tree = tree;
    }


    @Override
    public Set<ClassElement> getSuperClasses() {
        Set<ClassElement> elements = new HashSet<>();
        if (classInterfaceContext != null) {

            elements.add(new ObjcClassElementImpl(classInterfaceContext.superclassName().getText()));
        }

        return elements;
    }

    @Override
    public Set<InterfaceElement> getImplementInterface() {
        Set<InterfaceElement> elements = new HashSet<>();

        if (classInterfaceContext != null &&
                classInterfaceContext.protocolReferenceList() != null) {
            for (ObjectiveCParser.ProtocolNameContext name : classInterfaceContext.protocolReferenceList().protocolList().protocolName()) {
                String protocol = name.getText();
                elements.add(new ObjcProtocolElementImpl(protocol));
            }
        }

        if (anonymousCategoryInterfaceContexts != null &&
                anonymousCategoryInterfaceContexts.protocolReferenceList() != null) {
            for (ObjectiveCParser.ProtocolNameContext name : anonymousCategoryInterfaceContexts.protocolReferenceList().protocolList().protocolName()) {
                String protocol = name.getText();
                elements.add(new ObjcProtocolElementImpl(protocol));
            }
        }

        return elements;
    }


    @Override
    public Set<MethodElement> getImplementMethods() {
        Set<MethodElement> elements = new HashSet<>();
        if (classInterfaceContext != null) {
            ObjectiveCParser.InterfaceDeclarationListContext declarationListContext = classInterfaceContext.interfaceDeclarationList();
            if (declarationListContext != null) {

                List<ObjectiveCParser.ClassMethodDeclarationContext> methodList = declarationListContext.classMethodDeclaration();
                if (methodList != null) {
                    for (ObjectiveCParser.ClassMethodDeclarationContext method : methodList) {
                        String methodName = method.methodDeclaration().methodSelector().getText();
                        elements.add(new ObjcMethodElementImpl(methodName));
                    }
                }

                List<ObjectiveCParser.InstanceMethodDeclarationContext> instanceMethodList = declarationListContext.instanceMethodDeclaration();
                if (instanceMethodList != null) {
                    for (ObjectiveCParser.InstanceMethodDeclarationContext method : instanceMethodList) {
                        String methodName = method.methodDeclaration().methodSelector().getText();
                        elements.add(new ObjcMethodElementImpl(methodName));
                    }
                }
            }
        }
        return elements;
    }


    @Override
    public Set<ClassesDiagramElement> getClassesDiagramElements() {

        Set<ClassesDiagramElement> elements = new HashSet<>();

        ClassesDiagramElement e = new CDEClassImpl(this.getName());
        for (MethodElement method : this.getImplementMethods()) {
            e.addItem(new CDMethodImpl(method.getName(), CDItemTag.PUBLIC));
        }
        elements.add(e);

        return elements;
    }

    @Override
    public Set<ClassesDiagramRelation> getClassesDiagramRelations() {
        Set<ClassesDiagramRelation> elements = new HashSet<>();
        for (ClassElement e : this.getSuperClasses()) {
            elements.add(new CDRExtendImpl(new CDEClassImpl(this.getName()), new CDEClassImpl(e.getName())));
        }

        for (InterfaceElement i : this.getImplementInterface()) {
            elements.add(new CDRImplementImpl(new CDEClassImpl(this.getName()), new CDEClassImpl(i.getName())));
        }

        return elements;


    }


    public ObjectiveCParser.ClassInterfaceContext getClassInterfaceContext() {
        return classInterfaceContext;
    }

    public void setClassInterfaceContext(ObjectiveCParser.ClassInterfaceContext classInterfaceContext) {
        this.classInterfaceContext = classInterfaceContext;
    }

    public ObjectiveCParser.CategoryInterfaceContext getAnonymousCategoryInterfaceContexts() {
        return anonymousCategoryInterfaceContexts;
    }

    public void setAnonymousCategoryInterfaceContexts(ObjectiveCParser.CategoryInterfaceContext anonymousCategoryInterfaceContexts) {
        this.anonymousCategoryInterfaceContexts = anonymousCategoryInterfaceContexts;
    }

    public ObjectiveCParser.ClassImplementationContext getClassImplementationContext() {
        return classImplementationContext;
    }

    public void setClassImplementationContext(ObjectiveCParser.ClassImplementationContext classImplementationContext) {
        this.classImplementationContext = classImplementationContext;
    }

    public Set<ObjectiveCParser.CategoryInterfaceContext> getCategoryInterfaceContexts() {
        return categoryInterfaceContexts;
    }

    public void setCategoryInterfaceContexts(Set<ObjectiveCParser.CategoryInterfaceContext> categoryInterfaceContexts) {
        this.categoryInterfaceContexts = categoryInterfaceContexts;
    }

    public Set<ObjectiveCParser.CategoryImplementationContext> getCategoryImplementationContexts() {
        return categoryImplementationContexts;
    }

    public void setCategoryImplementationContexts(Set<ObjectiveCParser.CategoryImplementationContext> categoryImplementationContexts) {
        this.categoryImplementationContexts = categoryImplementationContexts;
    }
}
