import { Application, Category, Departement } from './appType';

const siCategory: Category = { id: 1, name: 'SI' };
const odaCategory: Category = { id: 2, name: 'ODA' };

const financeDepartement: Departement = { id: 1, name: 'Finance' };
const hrDepartement: Departement = { id: 2, name: 'Ressources Humaines' };
const marketingDepartement: Departement = { id: 3, name: 'Marketing' };
const salesDepartement: Departement = { id: 4, name: 'Ventes' };
const allDepartements: Departement = { id: 5, name: 'Tous les départements' };

export const applications: Application[] = [
  {
    id: 1,
    name: 'Budget Tracker',
    description: "Application de gestion des budgets d'entreprise.",
    businessValue: 50000,
    costBuild: 20000,
    costRun: 5000,
    category: siCategory,
    startDate: new Date('2023-01-15'),
    lastUpdate: new Date('2024-12-20'),
    status: 'development',
    departement: financeDepartement,
    time: 'invest',
    userTotal: 15,
    note: 0,
  },
  {
    id: 2,
    name: 'Employee Portal',
    description: 'Portail interne pour la gestion des employés.',
    businessValue: 30000,
    costBuild: 10000,
    costRun: 3000,
    category: siCategory,
    startDate: new Date('2021-06-01'),
    lastUpdate: new Date('2023-11-15'),
    status: 'production',
    departement: hrDepartement,
    time: 'tolerate',
    userTotal: 24,
    note: 0,
  },
  {
    id: 3,
    name: 'E-Commerce Platform',
    description: 'Plateforme de vente en ligne pour les clients.',
    businessValue: 100000,
    costBuild: 40000,
    costRun: 12000,
    category: odaCategory,
    startDate: new Date('2020-03-10'),
    lastUpdate: new Date('2024-08-01'),
    status: 'deprecated',
    departement: marketingDepartement,
    time: 'invest',
    userTotal: 25,
    note: 0,
  },
  {
    id: 4,
    name: 'Legacy CRM',
    description: 'Système de gestion de la relation client en fin de vie.',
    businessValue: 15000,
    costBuild: 25000,
    costRun: 7000,
    category: siCategory,
    startDate: new Date('2010-05-12'),
    lastUpdate: new Date('2020-12-01'),
    status: 'production',
    departement: salesDepartement,
    time: 'eliminate',
    userTotal: 20,
    note: 0,
  },
  {
    id: 5,
    name: 'Internal Messaging System',
    description: 'Application de messagerie interne utilisée par les employés.',
    businessValue: 20000,
    costBuild: 15000,
    costRun: 8000,
    category: siCategory,
    startDate: new Date('2015-04-10'),
    lastUpdate: new Date('2022-06-30'),
    status: 'production',
    departement: allDepartements,
    time: 'migrate',
    userTotal: 18,
    note: 0,
  },
];
