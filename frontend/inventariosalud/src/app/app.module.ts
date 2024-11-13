import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TableModule } from 'primeng/table';
import { ListaMedicamentosComponent } from './components/lista-medicamentos/lista-medicamentos.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NavbarComponent } from './components/navbar/navbar.component';
import { ToolbarModule } from 'primeng/toolbar'; 
import { AvatarModule } from 'primeng/avatar';
import { CommonModule } from '@angular/common';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { CalendarModule } from 'primeng/calendar';
import { RegistrarMedicamentoComponent } from './components/registrar-medicamento/registrar-medicamento.component';
import { DialogModule } from 'primeng/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    ListaMedicamentosComponent,
    RegistrarMedicamentoComponent // Declaración del componente Home
  ],
  imports: [
    FormsModule,
    BrowserModule,
    HttpClientModule, // Importa HttpClientModule
    AppRoutingModule,
    TableModule,// Módulo de la tabla PrimeNG
    ToolbarModule,
    AvatarModule,
    ButtonModule,
    CommonModule,
    InputTextModule,
    ButtonModule,
    CalendarModule,
    DialogModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
