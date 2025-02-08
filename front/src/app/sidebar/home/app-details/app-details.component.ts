import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { AppHistory, Application } from '../../../application-APM/appType';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { IconLifecycleComponent } from '../../../components/icons/icon-lifecycle/icon-lifecycle.component';
import { IconStarComponent } from '../../../components/icons/icon-star/icon-star.component';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { ApplicationService } from '../application.service';
import { TimelineComponent } from '../../../components/timeline/timeline.component';
import { TimelineHistoryComponent } from './timeline-history/timeline-history.component';
import { AppHistoryService } from '../../../application-APM/app-history.service';
import { Chart, ChartConfiguration } from 'chart.js/auto';
import { ChartData } from './appDetailType';
import { DateFormater } from '../../../lib/dateFormater';
import { RateApplicationComponent } from "./rate-application/rate-application.component";

@Component({
  selector: 'app-app-details',
  imports: [
    FormsModule,
    CommonModule,
    IconLifecycleComponent,
    IconStarComponent,
    RouterLink,
    TimelineComponent,
    TimelineHistoryComponent,
    RateApplicationComponent
],
  templateUrl: './app-details.component.html',
  styleUrl: './app-details.component.scss',
})
export class AppDetailsComponent implements OnInit {
  @ViewChild('chartCanvas') chartCanvas!: ElementRef<HTMLCanvasElement>;
  application: Application | null = null;
  appId: number | null = null;
  appHistory: AppHistory[] = [];
  activeTab: string = 'dashboard';
  chart: Chart | null = null;

  constructor(
    private appService: ApplicationService,
    private activateRoute: ActivatedRoute,
    private appHistoryService: AppHistoryService,
    private router: Router
  ) {}

  chartData = [
    { name: 'Jan', businessValue: 4000, costBuild: 2400, costRun: 2400 },
    { name: 'Feb', businessValue: 3000, costBuild: 1398, costRun: 2210 },
    { name: 'Mar', businessValue: 2000, costBuild: 9800, costRun: 2290 },
    { name: 'Apr', businessValue: 2780, costBuild: 3908, costRun: 2000 },
    { name: 'May', businessValue: 1890, costBuild: 4800, costRun: 2181 },
    { name: 'Jun', businessValue: 2390, costBuild: 3800, costRun: 2500 },
    { name: 'Jul', businessValue: 3490, costBuild: 4300, costRun: 2100 },
  ];

  generateChartData(appHistory: AppHistory[]): ChartData[] {
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

    const monthlyData: { [key: string]: ChartData } = allMonths.reduce(
      (acc, month) => {
        acc[month] = {
          name: month,
          businessValue: 0,
          costBuild: 0,
          costRun: 0,
        };
        return acc;
      },
      {} as { [key: string]: ChartData }
    );

    appHistory.forEach((history) => {
      const date = new Date(history.modifiedAt);
      const month = DateFormater.getMonth(date);

      if (monthlyData[month]) {
        monthlyData[month].businessValue += history.businessValue;
        monthlyData[month].costBuild += history.costBuild;
        monthlyData[month].costRun += history.costRun;
      }
    });

    return Object.values(monthlyData);
  }


  setActiveTab(tab: string) {
    this.activeTab = tab;
    if (tab === 'dashboard') {
      setTimeout(() => {
        this.initChart();
      }, 200);
    }
  }

  formatDate(date: Date | string) {
    return DateFormater.format(date);
  }

  ngOnInit() {
    this.appId = Number(this.activateRoute.snapshot.paramMap.get('id'));
    this.findAppById(this.appId);
    this.findAllAppHistory(this.appId);
    setTimeout(() => {
      this.initChart();
    }, 200);
  }

  findAppById(id: number) {
    this.appService.findById(id).subscribe({
      next: (val) => {
        if (val == null) {
          this.router.navigate(['/404']);
        } else {
          this.application = val;
        }
      },
    });
  }

  findAllAppHistory = (appId: number) => {
    this.appHistoryService.findAllByAppId(appId).subscribe({
      next: (data) => {
        this.appHistory = data;
        this.chartData = this.generateChartData(data);
      },
      error: (error) => {
        console.error('Erreur lors de la récupération des tâches :', error);
      },
    });
  };

  initChart() {
    if (this.chartCanvas) {
      const ctx = this.chartCanvas.nativeElement.getContext('2d');
      if (ctx) {
        const config: ChartConfiguration = {
          type: 'line',
          data: {
            labels: this.chartData.map((item) => item.name),
            datasets: [
              {
                label: 'Valeur Métier',
                data: this.chartData.map((item) => item.businessValue),
                borderColor: '#6366f1',
                backgroundColor: 'transparent',
                tension: 0.4,
                fill: false,
                borderWidth: 3,
                pointRadius: 4,
                pointBackgroundColor: '#6366f1',
                pointBorderColor: '#fff',
                pointBorderWidth: 2,
                pointHoverRadius: 6,
                pointHoverBorderWidth: 3,
              },
              {
                label: 'Coût de Build',
                data: this.chartData.map((item) => item.costBuild),
                borderColor: '#22c55e',
                backgroundColor: 'transparent',
                tension: 0.4,
                fill: false,
                borderWidth: 3,
                pointRadius: 4,
                pointBackgroundColor: '#22c55e',
                pointBorderColor: '#fff',
                pointBorderWidth: 2,
                pointHoverRadius: 6,
                pointHoverBorderWidth: 3,
              },
              {
                label: 'Coût de Run',
                data: this.chartData.map((item) => item.costRun),
                borderColor: '#f59e0b',
                backgroundColor: 'transparent',
                tension: 0.4,
                fill: false,
                borderWidth: 3,
                pointRadius: 4,
                pointBackgroundColor: '#f59e0b',
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

        this.chart = new Chart(ctx, config);
      }
    }
  }

  getStatusColor(status: Application['status']): string {
    switch (status) {
      case 'development':
        return 'bg-warning';
      case 'production':
        return 'bg-success';
      case 'deprecated':
        return 'bg-danger';
      default:
        return 'bg-secondary';
    }
  }

  getTimeColor(time: Application['time']): string {
    switch (time) {
      case 'tolerate':
        return 'text-warning';
      case 'invest':
        return 'text-success';
      case 'migrate':
        return 'text-info';
      case 'eliminate':
        return 'text-danger';
      default:
        return 'text-secondary';
    }
  }
}
