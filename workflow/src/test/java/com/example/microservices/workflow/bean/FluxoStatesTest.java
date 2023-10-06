package com.example.microservices.workflow.bean;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.*;

import static com.example.microservices.workflow.IsFluxoStatesFinalizado.isFluxoStatesFinalizado;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasValue;
//referencias hamcrest: https://www.baeldung.com/java-junit-hamcrest-guide
public class FluxoStatesTest {
    //é igual ignorando case sensitive?
    @Test
    public void given2Strings_whenEqual_thenCorrect() {
        String a = "CRIADO";
        String b = "criado";
        assertThat(a, equalToIgnoringCase(b));
    }
    //é lista vazia?
    @Test
    public void givenCollection_whenEmpty_thenCorrect() {
        List<String> emptyList = new ArrayList<>();
        assertThat(emptyList, empty());
    }

    //a lista é do tamanho esperado?
    @Test
    public void givenAList_whenChecksSize_thenCorrect() {
        var listCurrent = Arrays.asList(FlowEvents.CRIAR, FlowEvents.INICIAR, FlowEvents.FINALIZAR, FlowEvents.CANCELAR);

        List<FlowEvents> hamcrestMatchers = Arrays.stream(FlowEvents.values()).toList();
        assertThat(hamcrestMatchers, hasSize(listCurrent.size()));
    }

    //contém determinados membros, independentemente da ordem
    @Test
    public void givenAListAndValues_whenChecksListForGivenValues_thenCorrect() {
        List<FlowEvents> hamcrestMatchers = Arrays.asList(
                FlowEvents.FINALIZAR, FlowEvents.INICIAR, FlowEvents.CANCELAR);
        assertThat(hamcrestMatchers,
                containsInAnyOrder(FlowEvents.INICIAR, FlowEvents.CANCELAR, FlowEvents.FINALIZAR));
    }

    @Test//o toString possui estes valores?
    public void givenBean_whenToStringReturnsRequiredString_thenCorrect(){
        var flow = Flow.builder()
                .id(UUID.randomUUID().toString())
                .state(FlowStates.CRIADO.name())
                .event(FlowEvents.INICIAR.name())
                .dataType(DataKeys.DATA_TYPE.name())
                .dateTime(LocalDateTime.now()).build();
        String str = flow.toString();
        assertThat(flow,hasToString(str));
    }

    @Test//possui estas propriedades?
    public void givenBean_Flow_whenHasValue_thenCorrect(){
        var flow = Flow.builder()
                .id(UUID.randomUUID().toString())
                .state(FlowStates.CRIADO.name())
                .event(FlowEvents.INICIAR.name())
                .dataType(DataKeys.DATA_TYPE.name())
                .dateTime(LocalDateTime.now()).build();

        assertThat(flow, hasProperty("state"));
        assertThat(flow, hasProperty("event"));
        assertThat(flow, hasProperty("dataType"));
        assertThat(flow, hasProperty("dateTime"));
    }

    @Test//possui estas propriedades?
    public void givenBean_History_whenHasValue_thenCorrect(){
        var history = History.builder()
                .id(1l)
                .state(FlowStates.CRIADO.name())
                .flow(new Flow())
                .dateTime(LocalDateTime.now()).build();

        assertThat(history, hasProperty("id"));
        assertThat(history, hasProperty("state"));
        assertThat(history, hasProperty("flow"));
        assertThat(history, hasProperty("dateTime"));
    }

    @Test//foi inicializado com este valor?
    public void givenBean_whenHasCorrectValue_thenCorrect() {
        var fluxo = Flow.builder()
                .state(FlowStates.CRIADO.name())
                .build();
        assertThat(fluxo, hasProperty("state", equalTo("CRIADO")));
    }

    @Test//é um?
    public void given2Classes_whenOneInheritsFromOther_thenCorrect(){
        assertThat(Flow.class,typeCompatibleWith(AbstractEntity.class));
    }

    @Test//ambos foram construidos com os mesmos valores?
    public void given2Beans_whenHavingSameValues_thenCorrect() {
        var uuid = UUID.randomUUID().toString();

        var flow = Flow.builder()
                .id(uuid)
                .state(FlowStates.CRIADO.name())
                .build();
        var flow2 = Flow.builder()
                .id(uuid)
                .state(FlowStates.CRIADO.name())
                .build();
        assertThat(flow, samePropertyValuesAs(flow2));
    }
    @Test//os membros da coleção estão em determinada ordem?
    public void givenAListAndValues_whenChecksListForGivenValuesWithOrder_thenCorrectFluxoStates() {
        List<FlowStates> hamcrestMatchers = Arrays.asList(
                FlowStates.CRIADO, FlowStates.INICIADO, FlowStates.FINALIZADO, FlowStates.CANCELADO);
        assertThat(hamcrestMatchers,
                contains(FlowStates.CRIADO, FlowStates.INICIADO, FlowStates.FINALIZADO, FlowStates.CANCELADO));
    }
    @Test//os membros da coleção estão em determinada ordem?
    public void givenAListAndValues_whenChecksListForGivenValuesWithOrder_thenCorrectFluxoEvents() {
        List<FlowEvents> hamcrestMatchers = Arrays.asList(
                FlowEvents.INICIAR, FlowEvents.FINALIZAR, FlowEvents.CANCELAR);
        assertThat(hamcrestMatchers,
                contains(FlowEvents.INICIAR, FlowEvents.FINALIZAR, FlowEvents.CANCELAR));
    }

    //abordagens distintas de contains - begin

    @Test//contém este item na lista?
    public void givenArrayAndValue_whenValueFoundInArray_thenCorrect() {
        FlowStates[] hamcrestMatchers = FlowStates.values();
        assertThat(hamcrestMatchers, hasItemInArray(FlowStates.CRIADO));
    }

    @Test//contém este item na lista?
    public void givenValueAndArray_whenValueIsOneOfArrayElements_thenCorrect() {
        FlowEvents[] hamcrestMatchers = FlowEvents.values();
        assertThat(FlowEvents.INICIAR, isOneOf(hamcrestMatchers));
    }

    @Test//contém este item na lista?
    public void givenValueAndArray_whenValueFoundInArray_thenCorrect() {
        DataKeys[] array = DataKeys.values();
        assertThat(DataKeys.STATE, isIn(array));
    }
    //abordagens distintas de contains - end

    @Test//contém itens na lista, independentemente da ordem?
    public void givenArrayAndValues_whenValuesFoundInArray_thenCorrect() {
        DataKeys[] hamcrestMatchers = DataKeys.values();
        assertThat(hamcrestMatchers,
                arrayContainingInAnyOrder(DataKeys.STATE, DataKeys.DATA_TYPE, DataKeys.ID_FLOW));
    }

    @Test//contém itens na lista em determinada ordem?
    public void givenArrayAndValues_whenValuesFoundInArrayInOrder_thenCorrect() {
        FlowEvents[] hamcrestMatchers = FlowEvents.values();
        assertThat(hamcrestMatchers,
                arrayContaining(FlowEvents.CRIAR, FlowEvents.INICIAR, FlowEvents.FINALIZAR, FlowEvents.CANCELAR));
    }

    @Test//contém uma determinada chave no mapa?
    public void givenMapAndKey_whenKeyFoundInMap_thenCorrect() {
        Map<FlowStates, FlowEvents> map = new HashMap<>();
        map.put(FlowStates.CRIADO, FlowEvents.INICIAR);
        assertThat(map, hasKey(FlowStates.CRIADO));
    }

    @Test//contém um valor no mapa?
    public void givenMapAndValue_whenValueFoundInMap_thenCorrect() {
        Map<FlowStates, FlowEvents> map = new HashMap<>();
        map.put(FlowStates.CRIADO, FlowEvents.INICIAR);
        assertThat(map, hasValue(FlowEvents.INICIAR));
    }

    @Test//contém uma entrada chave e valor no mapa?
    public void givenMapAndEntry_whenEntryFoundInMap_thenCorrect() {
        Map<FlowStates, FlowEvents> map = new HashMap<>();
        map.put(FlowStates.CRIADO, FlowEvents.INICIAR);
        assertThat(map, hasEntry(FlowStates.CRIADO, FlowEvents.INICIAR));
    }

    @Test//contém um ou outro?//OR
    public void givenString_whenMeetsAnyOfGivenConditions_thenCorrect() {
        Map<FlowStates, FlowEvents> map = new HashMap<>();
        map.put(FlowStates.CRIADO, FlowEvents.INICIAR);

        assertThat(map, anyOf(
                hasValue(FlowEvents.INICIAR),
                hasValue(FlowEvents.FINALIZAR),
                hasValue(FlowEvents.CANCELAR)));
    }

    @Test//contém todos outro?//AND
    public void givenString_whenMeetsAllOfGivenConditions_thenCorrect() {
        Map<FlowStates, FlowEvents> map = new HashMap<>();
        map.put(FlowStates.CRIADO, FlowEvents.INICIAR);
        map.put(FlowStates.FINALIZADO, FlowEvents.FINALIZAR);
        map.put(FlowStates.CANCELADO, FlowEvents.CANCELAR);
        assertThat(map, allOf(
                hasValue(FlowEvents.INICIAR),
                hasValue(FlowEvents.FINALIZAR),
                hasValue(FlowEvents.CANCELAR)));
    }

    //Personalizando Matchers
    @Test
    public void givenInteger_whenAPositiveValue_thenCorrect() {
        assertThat(FlowStates.FINALIZADO, isFluxoStatesFinalizado());
    }
}
