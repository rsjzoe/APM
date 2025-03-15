import { Component, ElementRef, Input, ViewChild } from '@angular/core';
import {
  AppHistory,
  Application,
  Budget,
} from '../../../../application/appType';
import { ActivatedRoute, Router } from '@angular/router';
import { AppHistoryService } from '../../../../application/app-history.service';
import { BudgetService } from '../../../../application/budget.service';
import { UserService } from '../../../administration/user.service';
import { Chart, ChartConfiguration } from 'chart.js/auto';
import { ChartDataCost, ChartDataTechBusiness } from '../appDetailType';
import { DateFormater } from '../../../../lib/dateFormater';

@Component({
  selector: 'app-dashboard',
  imports: [],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss',
})
export class DashboardComponent {
  @Input() route!: string;
  @ViewChild('chartCanvasTechBusiness')
  chartCanvasTechBusiness!: ElementRef<HTMLCanvasElement>;
  @ViewChild('chartCanvasCost') chartCanvasCost!: ElementRef<HTMLCanvasElement>;

  application: Application | null = null;
  appId: number | null = null;
  appHistory: AppHistory[] = [];
  budgetHistory: Budget[] = [];
  chartTechBusiness: Chart | null = null;
  chartCost: Chart | null = null;

  constructor(
    private activateRoute: ActivatedRoute,
    private appHistoryService: AppHistoryService,
    private budgetService: BudgetService,
    public userService: UserService
  ) {}

  chartDataTechBusiness: ChartDataTechBusiness[] = [
    { name: 'Jan', businessValue: 1, techDebt: 2.1 },
    { name: 'Feb', businessValue: 2, techDebt: 3 },
    { name: 'Mar', businessValue: 3, techDebt: 1.5 },
    { name: 'Apr', businessValue: 1, techDebt: 2.5 },
    { name: 'May', businessValue: 4, techDebt: 2.8 },
    { name: 'Jun', businessValue: 5, techDebt: 4 },
    { name: 'Jul', businessValue: 2, techDebt: 1.5 },
  ];

  chartDataCost: ChartDataCost[] = [
    { name: 'Jan', costBuild: 100, costRun: 200 },
    { name: 'Feb', costBuild: 200, costRun: 300 },
    { name: 'Mar', costBuild: 300, costRun: 100 },
    { name: 'Apr', costBuild: 100, costRun: 200 },
    { name: 'May', costBuild: 400, costRun: 200 },
    { name: 'Jun', costBuild: 500, costRun: 400 },
    { name: 'Jul', costBuild: 200, costRun: 100 },
  ];

  // generateChartData(appHistory: AppHistory[]): ChartData[] {
  generateChartData(appHistory: Budget[]): {
    chartDataCost: ChartDataCost[];
    chartDataTechBusiness: ChartDataTechBusiness[];
  } {
    const allMonths = [
      'janv.',
      'févr.',
      'mars.',
      'avr.',
      'mai.',
      'juin.',
      'juil.',
      'août.',
      'sept.',
      'oct.',
      'nov.',
      'déc.',
    ];

    const monthlyDataTechBusiness: { [key: string]: ChartDataTechBusiness } =
      allMonths.reduce((acc, month) => {
        acc[month] = {
          name: month,
          businessValue: 0,
          techDebt: 0,
        };
        return acc;
      }, {} as { [key: string]: ChartDataTechBusiness });

    const monthlyDataCost: { [key: string]: ChartDataCost } = allMonths.reduce(
      (acc, month) => {
        acc[month] = {
          name: month,
          costBuild: 0,
          costRun: 0,
        };
        return acc;
      },
      {} as { [key: string]: ChartDataCost }
    );

    let lastBussinessValue = 0;
    let lastCostBuild = 0;
    let lastCostsRun = 0;
    appHistory.forEach((history) => {
      // const date = new Date(history.modifiedAt);
      const date = new Date(history.createdAt);
      const month = DateFormater.getMonth(date);

      if (monthlyDataTechBusiness[month]) {
        if (lastBussinessValue != history.businessValue) {
          monthlyDataTechBusiness[month].businessValue += history.businessValue;
          lastBussinessValue = history.businessValue;
        }
        if (lastCostBuild !== history.budgetBuild) {
          monthlyDataTechBusiness[month].techDebt += history.technicalDebt;
          lastCostBuild = history.budgetBuild;
        }
      }

      if (monthlyDataCost[month]) {
        if (lastBussinessValue != history.businessValue) {
          monthlyDataCost[month].costBuild += history.budgetBuild;
          lastBussinessValue = history.businessValue;
        }
        if (lastCostBuild !== history.budgetBuild) {
          monthlyDataCost[month].costRun += history.budgetRun;
          lastCostBuild = history.budgetBuild;
        }
      }
    });

    const chartDataCost: ChartDataCost[] = Object.values(monthlyDataCost).map(
      (data) => ({
        name: data.name,
        costBuild: data.costBuild,
        costRun: data.costRun,
      })
    );

    const chartDataTechBusiness: ChartDataTechBusiness[] = Object.values(
      monthlyDataTechBusiness
    ).map((data) => ({
      name: data.name,
      businessValue: data.businessValue,
      techDebt: 0, // Assuming techDebt is not available in monthlyData
    }));

    return { chartDataCost, chartDataTechBusiness };
  }

  findAllAppHistory = (appId: number) => {
    this.appHistoryService.findAllByAppId(appId).subscribe({
      next: (data) => {
        this.appHistory = data;
        // this.chartData = this.generateChartData(data);
      },
      error: (error) => {
        console.error('Erreur lors de la récupération des tâches :', error);
      },
    });
  };

  findAllBudgetHistory = (appId: number) => {
    this.budgetService.findAllByAppId(appId).subscribe({
      next: (data) => {
        this.budgetHistory = data;
        let chartData = this.generateChartData(data);
        this.chartDataCost = chartData.chartDataCost;
        this.chartDataTechBusiness = chartData.chartDataTechBusiness;
      },
      error: (error) => {
        console.error('Erreur lors de la récupération des tâches :', error);
      },
    });
  };

  initChartTechBusiness() {
    if (this.chartCanvasTechBusiness) {
      const ctx = this.chartCanvasTechBusiness.nativeElement.getContext('2d');
      if (ctx) {
        const config: ChartConfiguration = {
          type: 'line',
          data: {
            labels: this.chartDataTechBusiness.map((item) => item.name),
            datasets: [
              {
                label: 'Valeur Métier',
                data: this.chartDataTechBusiness.map(
                  (item) => item.businessValue
                ),
                borderColor: '#ff7900',
                backgroundColor: 'transparent',
                tension: 0.4,
                fill: false,
                borderWidth: 3,
                pointRadius: 4,
                pointBackgroundColor: '#ff7900',
                pointBorderColor: '#fff',
                pointBorderWidth: 2,
                pointHoverRadius: 6,
                pointHoverBorderWidth: 3,
              },
              {
                label: 'Dette Technique',
                data: this.chartDataTechBusiness.map((item) => item.techDebt),
                borderColor: '#27a971',
                backgroundColor: 'transparent',
                tension: 0.4,
                fill: false,
                borderWidth: 3,
                pointRadius: 4,
                pointBackgroundColor: '#27a971',
                pointBorderColor: '#fff',
                pointBorderWidth: 2,
                pointHoverRadius: 6,
                pointHoverBorderWidth: 3,
              },
            ],
          },
          options: {
            responsive: true,
            maintainAspectRatio: false,
            plugins: {
              legend: {
                position: 'top',
                labels: {
                  padding: 20,
                  font: {
                    size: 13,
                    family: "'Inter', sans-serif",
                  },
                  usePointStyle: true,
                  pointStyle: 'circle',
                },
              },
              tooltip: {
                mode: 'index',
                intersect: false,
                backgroundColor: 'rgba(255, 255, 255, 0.95)',
                titleColor: '#1f2937',
                bodyColor: '#4b5563',
                borderColor: '#e5e7eb',
                borderWidth: 1,
                padding: 12,
                bodyFont: {
                  size: 13,
                  family: "'Inter', sans-serif",
                },
                titleFont: {
                  size: 14,
                  family: "'Inter', sans-serif",
                },
                displayColors: true,
                boxWidth: 8,
                boxHeight: 8,
                boxPadding: 4,
                usePointStyle: true,
              },
            },
            hover: {
              mode: 'nearest',
              intersect: true,
            },
            scales: {
              x: {
                grid: {
                  display: false,
                },
                ticks: {
                  font: {
                    size: 12,
                    family: "'Inter', sans-serif",
                  },
                  padding: 8,
                },
              },
              y: {
                beginAtZero: true,
                grid: {
                  color: '#f3f4f6',
                },
                ticks: {
                  font: {
                    size: 12,
                    family: "'Inter', sans-serif",
                  },
                  padding: 12,
                  stepSize: 2000,
                  callback: function (value: any) {
                    return value.toLocaleString() + ' €';
                  },
                },
              },
            },
            elements: {
              line: {
                borderWidth: 3,
              },
            },
            layout: {
              padding: {
                top: 20,
                right: 20,
                bottom: 20,
                left: 20,
              },
            },
          },
        };

        this.chartTechBusiness = new Chart(ctx, config);
      }
    }
  }

  initChartCost() {
    if (this.chartCanvasCost) {
      const ctx = this.chartCanvasCost.nativeElement.getContext('2d');
      if (ctx) {
        const config: ChartConfiguration = {
          type: 'line',
          data: {
            labels: this.chartDataCost.map((item) => item.name),
            datasets: [
              {
                label: 'Coût de Construction',
                data: this.chartDataCost.map((item) => item.costBuild),
                borderColor: '#4bb4e6',
                backgroundColor: 'transparent',
                tension: 0.4,
                fill: false,
                borderWidth: 3,
                pointRadius: 4,
                pointBackgroundColor: '#4bb4e6',
                pointBorderColor: '#fff',
                pointBorderWidth: 2,
                pointHoverRadius: 6,
                pointHoverBorderWidth: 3,
              },
              {
                label: "Coût d'Exploitation",
                data: this.chartDataCost.map((item) => item.costRun),
                borderColor: '#ff8ad4',
                backgroundColor: 'transparent',
                tension: 0.4,
                fill: false,
                borderWidth: 3,
                pointRadius: 4,
                pointBackgroundColor: '#ff8ad4',
                pointBorderColor: '#fff',
                pointBorderWidth: 2,
                pointHoverRadius: 6,
                pointHoverBorderWidth: 3,
              },
            ],
          },
          options: {
            responsive: true,
            maintainAspectRatio: false,
            plugins: {
              legend: {
                position: 'top',
                labels: {
                  padding: 20,
                  font: {
                    size: 13,
                    family: "'Inter', sans-serif",
                  },
                  usePointStyle: true,
                  pointStyle: 'circle',
                },
              },
              tooltip: {
                mode: 'index',
                intersect: false,
                backgroundColor: 'rgba(255, 255, 255, 0.95)',
                titleColor: '#1f2937',
                bodyColor: '#4b5563',
                borderColor: '#e5e7eb',
                borderWidth: 1,
                padding: 12,
                bodyFont: {
                  size: 13,
                  family: "'Inter', sans-serif",
                },
                titleFont: {
                  size: 14,
                  family: "'Inter', sans-serif",
                },
                displayColors: true,
                boxWidth: 8,
                boxHeight: 8,
                boxPadding: 4,
                usePointStyle: true,
              },
            },
            hover: {
              mode: 'nearest',
              intersect: true,
            },
            scales: {
              x: {
                grid: {
                  display: false,
                },
                ticks: {
                  font: {
                    size: 12,
                    family: "'Inter', sans-serif",
                  },
                  padding: 8,
                },
              },
              y: {
                beginAtZero: true,
                grid: {
                  color: '#f3f4f6',
                },
                ticks: {
                  font: {
                    size: 12,
                    family: "'Inter', sans-serif",
                  },
                  padding: 12,
                  stepSize: 2000,
                  callback: function (value: any) {
                    return value.toLocaleString() + ' €';
                  },
                },
              },
            },
            elements: {
              line: {
                borderWidth: 3,
              },
            },
            layout: {
              padding: {
                top: 20,
                right: 20,
                bottom: 20,
                left: 20,
              },
            },
          },
        };

        this.chartTechBusiness = new Chart(ctx, config);
      }
    }
  }

  ngOnInit() {
    this.appId = Number(this.activateRoute.snapshot.paramMap.get('id'));
    this.findAllAppHistory(this.appId);
    this.findAllBudgetHistory(this.appId);
    setTimeout(() => {
      this.initChartCost();
      this.initChartTechBusiness();
    }, 1000);
  }
}
