package com.bootstrap.api.service

import org.springframework.stereotype.Service

@Service
class HumanCapitalService (
    val salesPersonRepository: SalesPersonRepository,
    val googleCloudStorage: GoogleCloudStorage
): HumanCapital {
    override fun allSalesPeople(): Iterable<SalesPerson> = salesPersonRepository.findAll()
    override fun salesPersonById(salesPersonId: Long): SalesPerson = salesPersonRepository.findById(salesPersonId).orElse(SalesPerson())
    override fun last4QuartersPerformance(salesPersonId: Long): SalesPersonPerformanceReport {
        val salesPerson = salesPersonRepository.findById(salesPersonId).get()
        return if(salesPerson.id == null) SalesPersonPerformanceReport() else SalesPersonPerformanceReport(
            salesPerson,
            googleCloudStorage.last4QuartersPerformance(salesPerson.name!!)!!
        )
    }
}