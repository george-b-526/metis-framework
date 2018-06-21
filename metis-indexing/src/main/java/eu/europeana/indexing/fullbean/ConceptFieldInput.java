package eu.europeana.indexing.fullbean;

import java.util.List;
import java.util.Map;
import eu.europeana.corelib.definitions.jibx.Concept;
import eu.europeana.corelib.solr.entity.ConceptImpl;
import eu.europeana.corelib.utils.StringArrayUtils;

/**
 * Converts a {@link Concept} from an {@link eu.europeana.corelib.definitions.jibx.RDF} to a
 * {@link ConceptImpl} for a {@link eu.europeana.corelib.definitions.edm.beans.FullBean}.
 */
final class ConceptFieldInput {

  ConceptImpl createNewConcept(Concept concept) {
    ConceptImpl conceptMongo = new ConceptImpl();
    conceptMongo.setAbout(concept.getAbout());
    if (concept.getChoiceList() != null) {
      for (Concept.Choice choice : concept.getChoiceList()) {
        if (choice.ifNote()) {
          if (conceptMongo.getNote() == null) {
            conceptMongo.setNote(FieldInputUtils.createLiteralMapFromString(choice.getNote()));
          } else {
            Map<String, List<String>> tempMap = conceptMongo.getNote();
            tempMap.putAll(FieldInputUtils.createLiteralMapFromString(choice.getNote()));
            conceptMongo.setNote(tempMap);
          }
        }
        if (choice.ifBroader()) {
          conceptMongo.setBroader(StringArrayUtils.addToArray(conceptMongo.getBroader(),
              FieldInputUtils.getResourceString(choice.getBroader())));
        }
        if (choice.ifBroadMatch()) {
          conceptMongo.setBroadMatch(StringArrayUtils.addToArray(conceptMongo.getBroadMatch(),
              FieldInputUtils.getResourceString(choice.getBroadMatch())));
        }
        if (choice.ifCloseMatch()) {
          conceptMongo.setCloseMatch(StringArrayUtils.addToArray(conceptMongo.getCloseMatch(),
              FieldInputUtils.getResourceString(choice.getCloseMatch())));
        }
        if (choice.ifExactMatch()) {
          conceptMongo.setExactMatch(StringArrayUtils.addToArray(conceptMongo.getExactMatch(),
              FieldInputUtils.getResourceString(choice.getExactMatch())));
        }
        if (choice.ifNarrower()) {
          conceptMongo.setNarrower(StringArrayUtils.addToArray(conceptMongo.getNarrower(),
              FieldInputUtils.getResourceString(choice.getNarrower())));
        }
        if (choice.ifNarrowMatch()) {
          conceptMongo.setNarrowMatch(StringArrayUtils.addToArray(conceptMongo.getNarrowMatch(),
              FieldInputUtils.getResourceString(choice.getNarrowMatch())));
        }
        if (choice.ifNotation()) {
          if (conceptMongo.getNotation() == null) {
            conceptMongo
                .setNotation(FieldInputUtils.createLiteralMapFromString(choice.getNotation()));
          } else {
            Map<String, List<String>> tempMap = conceptMongo.getNotation();
            tempMap.putAll(FieldInputUtils.createLiteralMapFromString(choice.getNotation()));
            conceptMongo.setNotation(tempMap);
          }
        }
        if (choice.ifRelated()) {
          conceptMongo.setRelated(StringArrayUtils.addToArray(conceptMongo.getRelated(),
              FieldInputUtils.getResourceString(choice.getRelated())));
        }
        if (choice.ifRelatedMatch()) {
          conceptMongo.setCloseMatch(StringArrayUtils.addToArray(conceptMongo.getRelatedMatch(),
              FieldInputUtils.getResourceString(choice.getRelatedMatch())));
        }

        if (choice.ifPrefLabel()) {
          if (conceptMongo.getPrefLabel() == null) {
            conceptMongo
                .setPrefLabel(FieldInputUtils.createLiteralMapFromString(choice.getPrefLabel()));
          } else {
            Map<String, List<String>> tempMap = conceptMongo.getPrefLabel();
            tempMap.putAll(FieldInputUtils.createLiteralMapFromString(choice.getPrefLabel()));
            conceptMongo.setPrefLabel(tempMap);
          }
        }

        if (choice.ifAltLabel()) {
          if (conceptMongo.getAltLabel() == null) {
            conceptMongo
                .setAltLabel(FieldInputUtils.createLiteralMapFromString(choice.getAltLabel()));
          } else {
            Map<String, List<String>> tempMap = conceptMongo.getAltLabel();
            tempMap.putAll(FieldInputUtils.createLiteralMapFromString(choice.getAltLabel()));
            conceptMongo.setAltLabel(tempMap);
          }
        }
      }

    }
    return conceptMongo;
  }
}
