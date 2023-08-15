package com.example.microservices.workflow.bean;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
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
        List<FluxoEvents> hamcrestMatchers = Arrays.stream(FluxoEvents.values()).toList();
        assertThat(hamcrestMatchers, hasSize(3));
    }

    //contém determinados membros, independentemente da ordem
    @Test
    public void givenAListAndValues_whenChecksListForGivenValues_thenCorrect() {
        List<FluxoEvents> hamcrestMatchers = Arrays.asList(
                FluxoEvents.FINALIZAR, FluxoEvents.INICIAR, FluxoEvents.CANCELAR);
        assertThat(hamcrestMatchers,
                containsInAnyOrder(FluxoEvents.INICIAR, FluxoEvents.CANCELAR, FluxoEvents.FINALIZAR));
    }

    @Test//o toString possui estes valores?
    public void givenBean_whenToStringReturnsRequiredString_thenCorrect(){
        var fluxo = Fluxo.builder()
                .id(1L)
                .state(FluxoStates.CRIADO.name())
                .event(FluxoEvents.INICIAR.name())
                .dataType(DataKeys.DATA_TYPE.name())
                .localDate(LocalDate.now()).build();
        String str=fluxo.toString();
        assertThat(fluxo,hasToString(str));
    }

    @Test//possui estas propriedades?
    public void givenBean_whenHasValue_thenCorrect(){
        var fluxo = Fluxo.builder()
                .id(1L)
                .state(FluxoStates.CRIADO.name())
                .event(FluxoEvents.INICIAR.name())
                .dataType(DataKeys.DATA_TYPE.name())
                .localDate(LocalDate.now()).build();

        assertThat(fluxo, hasProperty("state"));
        assertThat(fluxo, hasProperty("event"));
        assertThat(fluxo, hasProperty("dataType"));
        assertThat(fluxo, hasProperty("localDate"));
    }

    @Test//foi inicializado com este valor?
    public void givenBean_whenHasCorrectValue_thenCorrect() {
        var fluxo = Fluxo.builder()
                .state(FluxoStates.CRIADO.name())
                .build();
        assertThat(fluxo, hasProperty("state", equalTo("CRIADO")));
    }

    @Test//é um?
    public void given2Classes_whenOneInheritsFromOther_thenCorrect(){
        assertThat(Fluxo.class,typeCompatibleWith(AbstractEntity.class));
    }

    @Test//ambos foram construidos com os mesmos valores?
    public void given2Beans_whenHavingSameValues_thenCorrect() {
        var fluxo1 = Fluxo.builder()
                .state(FluxoStates.CRIADO.name())
                .build();
        var fluxo2 = Fluxo.builder()
                .state(FluxoStates.CRIADO.name())
                .build();
        assertThat(fluxo1, samePropertyValuesAs(fluxo2));
    }
    @Test//os membros da coleção estão em determinada ordem?
    public void givenAListAndValues_whenChecksListForGivenValuesWithOrder_thenCorrectFluxoStates() {
        List<FluxoStates> hamcrestMatchers = Arrays.asList(
                FluxoStates.CRIADO, FluxoStates.INICIADO, FluxoStates.FINALIZADO, FluxoStates.CANCELADO);
        assertThat(hamcrestMatchers,
                contains(FluxoStates.CRIADO, FluxoStates.INICIADO, FluxoStates.FINALIZADO, FluxoStates.CANCELADO));
    }
    @Test//os membros da coleção estão em determinada ordem?
    public void givenAListAndValues_whenChecksListForGivenValuesWithOrder_thenCorrectFluxoEvents() {
        List<FluxoEvents> hamcrestMatchers = Arrays.asList(
                FluxoEvents.INICIAR, FluxoEvents.FINALIZAR, FluxoEvents.CANCELAR);
        assertThat(hamcrestMatchers,
                contains(FluxoEvents.INICIAR, FluxoEvents.FINALIZAR, FluxoEvents.CANCELAR));
    }

    //abordagens distintas de contains - begin

    @Test//contém este item na lista?
    public void givenArrayAndValue_whenValueFoundInArray_thenCorrect() {
        FluxoStates[] hamcrestMatchers = FluxoStates.values();
        assertThat(hamcrestMatchers, hasItemInArray(FluxoStates.CRIADO));
    }

    @Test//contém este item na lista?
    public void givenValueAndArray_whenValueIsOneOfArrayElements_thenCorrect() {
        FluxoEvents[] hamcrestMatchers = FluxoEvents.values();
        assertThat(FluxoEvents.INICIAR, isOneOf(hamcrestMatchers));
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
                arrayContainingInAnyOrder(DataKeys.STATE, DataKeys.DATA_TYPE, DataKeys.ID_FLUXO));
    }

    @Test//contém itens na lista em determinada ordem?
    public void givenArrayAndValues_whenValuesFoundInArrayInOrder_thenCorrect() {
        FluxoEvents[] hamcrestMatchers = FluxoEvents.values();
        assertThat(hamcrestMatchers,
                arrayContaining(FluxoEvents.INICIAR, FluxoEvents.FINALIZAR, FluxoEvents.CANCELAR));
    }

    @Test//contém uma determinada chave no mapa?
    public void givenMapAndKey_whenKeyFoundInMap_thenCorrect() {
        Map<FluxoStates, FluxoEvents> map = new HashMap<>();
        map.put(FluxoStates.CRIADO, FluxoEvents.INICIAR);
        assertThat(map, hasKey(FluxoStates.CRIADO));
    }

    @Test//contém um valor no mapa?
    public void givenMapAndValue_whenValueFoundInMap_thenCorrect() {
        Map<FluxoStates, FluxoEvents> map = new HashMap<>();
        map.put(FluxoStates.CRIADO, FluxoEvents.INICIAR);
        assertThat(map, hasValue(FluxoEvents.INICIAR));
    }

    @Test//contém uma entrada chave e valor no mapa?
    public void givenMapAndEntry_whenEntryFoundInMap_thenCorrect() {
        Map<FluxoStates, FluxoEvents> map = new HashMap<>();
        map.put(FluxoStates.CRIADO, FluxoEvents.INICIAR);
        assertThat(map, hasEntry(FluxoStates.CRIADO, FluxoEvents.INICIAR));
    }

    @Test//contém um ou outro?//OR
    public void givenString_whenMeetsAnyOfGivenConditions_thenCorrect() {
        Map<FluxoStates, FluxoEvents> map = new HashMap<>();
        map.put(FluxoStates.CRIADO, FluxoEvents.INICIAR);

        assertThat(map, anyOf(
                hasValue(FluxoEvents.INICIAR),
                hasValue(FluxoEvents.FINALIZAR),
                hasValue(FluxoEvents.CANCELAR)));
    }

    @Test//contém todos outro?//AND
    public void givenString_whenMeetsAllOfGivenConditions_thenCorrect() {
        Map<FluxoStates, FluxoEvents> map = new HashMap<>();
        map.put(FluxoStates.CRIADO, FluxoEvents.INICIAR);
        map.put(FluxoStates.FINALIZADO, FluxoEvents.FINALIZAR);
        map.put(FluxoStates.CANCELADO, FluxoEvents.CANCELAR);
        assertThat(map, allOf(
                hasValue(FluxoEvents.INICIAR),
                hasValue(FluxoEvents.FINALIZAR),
                hasValue(FluxoEvents.CANCELAR)));
    }

    //Personalizando Matchers
    @Test
    public void givenInteger_whenAPositiveValue_thenCorrect() {
        assertThat(FluxoStates.FINALIZADO, isFluxoStatesFinalizado());
    }
}
