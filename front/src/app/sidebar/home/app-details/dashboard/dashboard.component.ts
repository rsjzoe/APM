import { Component, ElementRef, Input, ViewChild } from '@angular/core';
import { AppHistory, Application } from '../../../../application/app.type';
import { ActivatedRoute, Router } from '@angular/router';
import { AppHistoryService } from '../../../../application/app-history.service';
import { UserService } from '../../../administration/user.service';
import { Chart, ChartConfiguration } from 'chart.js/auto';
import { ChartDataCost, ChartDataTechBusiness } from '../appDetailType';
import { DateFormater } from '../../../../lib/dateFormater';
import { Cost, CostMonth } from '../../../../application/cost/cost.type';
import { CommonModule } from '@angular/common';
import { TechBusinessValueMonth } from '../../../../application/tech-business-value/techBusinessValue.type';
import { CostService } from '../../../../application/cost/cost.service';
import { TechBusinessValueService } from '../../../../application/tech-business-value/tech-business-value.service';

@Component({
  selector: 'app-dashboard',
  imports: [CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss',
})
export class DashboardComponent {
  @Input() route!: string;
  @ViewChild('chartCanvasTechBusiness')
  chartCanvasTechBusiness!: ElementRef<HTMLCanvasElement>;
  @ViewChild('chartCanvasCost') chartCanvasCost!: ElementRef<HTMLCanvasElement>;
  activeTab: 'cost' | 'techBusiness' = 'techBusiness';
  loading = false;

  application: Application | null = null;
  appId: number | null = null;
  appHistory: AppHistory[] = [];
  chartTechBusiness: Chart | null = null;
  chartCost: Chart | null = null;
  costsMonth: CostMonth[] = [];
  techBusinessValueMonth: TechBusinessValueMonth[] = [];

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

  constructor(
    private activateRoute: ActivatedRoute,
    private appHistoryService: AppHistoryService,
    public userService: UserService,
    private costService: CostService,
    private techBusinessValueService: TechBusinessValueService
  ) {}

  setActiveTab(tab: 'cost' | 'techBusiness') {
    this.activeTab = tab;
    this.initChart();
  }

  initChart() {
    this.loading = true;
    setTimeout(() => {
      if (this.activeTab === 'cost') {
        this.initChartCost();
      } else {
        this.initChartTechBusiness();
      }
      this.loading = false;
    }, 1000);
  }

  findAllCostsMonth() {
    if (this.appId == null) return;
    this.costService.findCostLatestPerMonthByAppId(this.appId).subscribe({
      next: (data) => {
        this.costsMonth = data;
        this.chartDataCost = this.generateChartCostData(data);
      },
      error: (error) => {
        console.error('Erreur lors de la récupération des tâches :', error);
      },
    });
  }

  findAllTechBusinessValueMonth() {
    if (this.appId == null) return;
    this.techBusinessValueService
      .findTechBusinessValueLatestPerMonthByAppId(this.appId)
      .subscribe({
        next: (data) => {
          this.techBusinessValueMonth = data;
          this.chartDataTechBusiness = this.generateChartTechBusinessData(data);
        },
        error: (error) => {
          console.error('Erreur lors de la récupération des tâches :', error);
        },
      });
  }

  generateChartCostData(costsMonth: CostMonth[]): ChartDataCost[] {
    return costsMonth.map((cost) => ({
      name: DateFormater.longToShortMonth(cost.monthValue - 1),
      costBuild: cost.data?.costBuild ?? 0,
      costRun: cost.data?.costRun ?? 0,
    }));
  }

  generateChartTechBusinessData(
    techBusinessValueMonth: TechBusinessValueMonth[]
  ): ChartDataTechBusiness[] {
    return techBusinessValueMonth.map((techBusiness) => ({
      name: DateFormater.longToShortMonth(techBusiness.monthValue - 1),
      businessValue: techBusiness.data?.businessValue ?? 0,
      techDebt: techBusiness.data?.technicalDebt ?? 0,
    }));
  }

  ngOnInit() {
    this.appId = Number(this.activateRoute.snapshot.paramMap.get('id'));
    this.findAllCostsMonth();
    this.findAllTechBusinessValueMonth();
    this.initChart();
  }

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
}
