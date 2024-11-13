import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListaMedicamentosComponent } from './components/lista-medicamentos/lista-medicamentos.component';
import { RegistrarMedicamentoComponent } from './components/registrar-medicamento/registrar-medicamento.component';


const routes: Routes = [
  { path: 'lista', component: ListaMedicamentosComponent },
  { path: 'registrar', component: RegistrarMedicamentoComponent},
  { path: '**', redirectTo: 'lista' } // Ruta para el componente Home
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
