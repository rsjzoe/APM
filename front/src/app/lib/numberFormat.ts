export class NumberFormat {
  static formatDevise(montant: number): string {
    return montant.toLocaleString('fr-FR', {});
  }
}
