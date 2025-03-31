package org.acme.application.app.usecase;

import org.acme.application.domain.output.ApplicationHistoryOutput;
import org.acme.application.domain.output.ApplicationOutput;
import org.acme.application.domain.port.GenerateDescriptionHistory;

public class GenerateDescription implements GenerateDescriptionHistory {

    @Override
    public String generate(ApplicationOutput app, ApplicationHistoryOutput lastHistory) {
        StringBuilder description = new StringBuilder("");

        if (lastHistory == null) {
            return "L'application a été créée.";
        }

        if (app.isDeleted()) {
            return "L'application a été supprimée.";
        }

        if (!app.getName().equals(lastHistory.getName())) {
            description.append("- Nom changé de '").append(lastHistory.getName())
                    .append("' à '").append(app.getName()).append("'.\n");
        }

        if (!app.getDescription().equals(lastHistory.getDescription())) {
            description.append("- Description modifiée.\n");
        }

        if (!app.getCategory().getId().equals(lastHistory.getCategory().getId())) {
            description.append("- Catégorie changée de '").append(lastHistory.getCategory().getName())
                    .append("' à '").append(app.getCategory().getName()).append("'.\n");
        }

        if (!app.getClasse().getId().equals(lastHistory.getClasse().getId())) {
            description.append("- Classe de l'application changé de '").append(lastHistory.getClasse().getName())
                    .append("' à '").append(app.getClasse().getName()).append("'.\n");
        }

        if (!app.getDepartement().getId().equals(lastHistory.getDepartement().getId())) {
            description.append("- Département changé de '").append(lastHistory.getDepartement().getName())
                    .append("' à '").append(app.getDepartement().getName()).append("'.\n");
        }

        if (app.getNoteBusinessValue() != lastHistory.getNoteBusinessValue()) {
            description.append("- Valeur métier changée de ").append(lastHistory.getNoteBusinessValue())
                    .append(" à ").append(app.getNoteBusinessValue()).append(".\n");
        }

        if (app.getNoteTechnicalDebt() != lastHistory.getNoteTechnicalDebt()) {
            description.append("- Dette Technique changée de ").append(lastHistory.getNoteTechnicalDebt())
                    .append(" à ").append(app.getNoteTechnicalDebt()).append(".\n");
        }

        if (app.getCurrentCost().getCostBuild() != lastHistory.getCost().getCostBuild()) {
            description.append("- Coût de construction changé de ")
                    .append(lastHistory.getCost().getCostBuild()).append(" à ")
                    .append(app.getCurrentCost().getCostBuild());
        }

        if (app.getCurrentCost().getCostRun() != lastHistory.getCost().getCostRun()) {
            description.append("- Coût de fonctionnement changé de ")
                    .append(lastHistory.getCost().getCostRun()).append(" à ")
                    .append(app.getCurrentCost().getCostRun());

        }

        if (app.getCurrentTechBusinessValue().getTechnicalDebt() != lastHistory.getTechBusinessValue()
                .getTechnicalDebt()) {
            description.append("- Dette technique actuelle changée de ")
                    .append(lastHistory.getTechBusinessValue().getTechnicalDebt()).append(" à ")
                    .append(app.getCurrentTechBusinessValue().getTechnicalDebt());
        }

        if (app.getCurrentTechBusinessValue().getBusinessValue() != lastHistory.getTechBusinessValue()
                .getBusinessValue()) {
            description.append("- Valeur métier changée de ")
                    .append(lastHistory.getTechBusinessValue().getBusinessValue()).append(" à ")
                    .append(app.getCurrentTechBusinessValue().getBusinessValue());
        }

        if (!app.getStatus().equals(lastHistory.getStatus())) {
            description.append("- Statut modifié de '").append(lastHistory.getStatus())
                    .append("' à '").append(app.getStatus()).append("'.\n");
        }

        if (app.getTime() != null && !app.getTime().equals(lastHistory.getTime())) {
            description.append("- TIME modifié de ").append(lastHistory.getTime())
                    .append("' à '").append(app.getTime()).append("'.\n");
            ;
        }

        if (!app.getLastUpdate().equals(lastHistory.getLastUpdate())) {
            description.append("- Date de dernière mise à jour modifiée de ").append(lastHistory.getLastUpdate())
                    .append(" à ").append(app.getLastUpdate()).append(".\n");
            ;
        }

        if (!app.getStartDate().equals(lastHistory.getStartDate())) {
            description.append("- Date de mise en production modifiée de ").append(lastHistory.getStartDate())
                    .append(" à ").append(app.getStartDate()).append(".\n");
            ;
        }

        return description.toString();
    }
}
