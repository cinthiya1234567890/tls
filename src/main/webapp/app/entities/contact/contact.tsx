import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './contact.reducer';
import { IContact } from 'app/shared/model/contact.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IContactProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Contact = (props: IContactProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { contactList, match, loading } = props;
  return (
    <div>
      <h2 id="contact-heading">
        Contacts
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Contact
        </Link>
      </h2>
      <div className="table-responsive">
        {contactList && contactList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Initiated Date</th>
                <th>Updated Date</th>
                <th>Contact Status</th>
                <th>Sender</th>
                <th>Receiver</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {contactList.map((contact, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${contact.id}`} color="link" size="sm">
                      {contact.id}
                    </Button>
                  </td>
                  <td>
                    {contact.initiatedDate ? <TextFormat type="date" value={contact.initiatedDate} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{contact.updatedDate ? <TextFormat type="date" value={contact.updatedDate} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>
                    {contact.contactStatusParamTitle ? (
                      <Link to={`cascader-param/${contact.contactStatusId}`}>{contact.contactStatusParamTitle}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{contact.senderId ? <Link to={`profile/${contact.senderId}`}>{contact.senderId}</Link> : ''}</td>
                  <td>{contact.receiverId ? <Link to={`profile/${contact.receiverId}`}>{contact.receiverId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${contact.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${contact.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${contact.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Contacts found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ contact }: IRootState) => ({
  contactList: contact.entities,
  loading: contact.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Contact);
